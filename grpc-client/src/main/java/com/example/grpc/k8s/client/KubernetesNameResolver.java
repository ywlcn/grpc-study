package com.example.grpc.k8s.client;

import io.fabric8.kubernetes.api.model.Endpoints;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.internal.SharedResourceHolder;

import javax.annotation.concurrent.GuardedBy;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class KubernetesNameResolver extends NameResolver {
	private final String namespace;
	private final String name;
	private final int port;
	private final Attributes params;
	private final SharedResourceHolder.Resource<ScheduledExecutorService> timerServiceResource;
	private final SharedResourceHolder.Resource<Executor> sharedChannelExecutorResource;
	private final KubernetesClient kubernetesClient;
	private Listener listener;

	private volatile boolean refreshing = false;
	private volatile boolean watching = false;

	public KubernetesNameResolver(String namespace, String name, int port, Attributes params,
			SharedResourceHolder.Resource<ScheduledExecutorService> timerServiceResource,
			SharedResourceHolder.Resource<Executor> sharedChannelExecutorResource) {
		this.namespace = namespace;
		this.name = name;
		this.port = port;
		this.params = params;
		this.timerServiceResource = timerServiceResource;
		this.sharedChannelExecutorResource = sharedChannelExecutorResource;
		this.kubernetesClient = new DefaultKubernetesClient();
	}

	//　サーバ接続する際の認証情報を返却する
	@Override
	public String getServiceAuthority() {
		return kubernetesClient.getMasterUrl().getAuthority();
	}

	// gRPCサービスを起動した際に呼び出される箇所？
	@Override
	public void start(Listener listener) {
		this.listener = listener;
		refresh();
	}

	@Override
	public void shutdown() {
		kubernetesClient.close();
	}

	@Override
	@GuardedBy("this")
	public void refresh() {
		if (refreshing)
			return;
		try {
			refreshing = true;

			Endpoints endpoints = kubernetesClient.endpoints().inNamespace(namespace).withName(name).get();

			if (endpoints == null) {
				// Didn't find anything, retrying
				ScheduledExecutorService timerService = SharedResourceHolder.get(timerServiceResource);
				timerService.schedule(() -> {
					refresh();
				}, 30, TimeUnit.SECONDS);
				return;
			}else {
				
				List<String> address = new ArrayList<>();
				endpoints.getSubsets().forEach(f -> {					
					f.getAddresses().forEach( g -> address.add(g.getIp()) );					
				});
				System.out.println("ServerList:" + address.toString() );
			}

			update(endpoints);
			watch();
		} finally {
			refreshing = false;
		}
	}

	private void update(Endpoints endpoints) {
		List<EquivalentAddressGroup> servers = new ArrayList<>();
		if (endpoints.getSubsets() == null)
			return;
		endpoints.getSubsets().stream().forEach(subset -> {
			long matchingPorts = subset.getPorts().stream().filter(p -> {
				return p != null && p.getPort() == port;
			}).count();
			if (matchingPorts > 0) {
				subset.getAddresses().stream().map(address -> {
					return new EquivalentAddressGroup(new InetSocketAddress(address.getIp(), port));
				}).forEach(address -> {
					servers.add(address);
				});
			}
		});

		listener.onAddresses(servers, Attributes.EMPTY);
	}

	@GuardedBy("this")
	protected void watch() {
		if (watching)
			return;
		watching = true;

		// サービスを監視し、変更があったら、Updateメソッドを実施して、接続一覧を更新する
		kubernetesClient.endpoints().inNamespace(namespace).withName(name).watch(new Watcher<Endpoints>() {
			
			@Override
			public void eventReceived(Action action, Endpoints endpoints) {
				switch (action) {
				case MODIFIED:
				case ADDED:
					update(endpoints);
					return;
				case DELETED:
					listener.onAddresses(Collections.emptyList(), Attributes.EMPTY);
					return;
				}
			}

			@Override
			public void onClose(WatcherException e) {
				watching = false;
			}
		});
	}
}