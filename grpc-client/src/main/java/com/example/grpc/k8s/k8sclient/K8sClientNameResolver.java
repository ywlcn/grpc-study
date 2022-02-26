package com.example.grpc.k8s.k8sclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.concurrent.GuardedBy;

import com.google.gson.reflect.TypeToken;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.internal.SharedResourceHolder;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Endpoints;
import io.kubernetes.client.openapi.models.V1EndpointsList;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Watch;
import io.kubernetes.client.util.Watch.Response;
import okhttp3.Call;

public class K8sClientNameResolver extends NameResolver {
	private final String namespace;
	private final String name;
	private final int port;
	private final Args args;
	private final SharedResourceHolder.Resource<ScheduledExecutorService> timeServiceResource;
	private final SharedResourceHolder.Resource<Executor> sharedChannelExecutorResource;
	private Listener listener;
	private ApiClient client;

	private volatile boolean refreshing = false;
	private volatile boolean watching = false;

	public K8sClientNameResolver(String namespace, String name, int port, Args args,
			SharedResourceHolder.Resource<ScheduledExecutorService> timeServiceResource,
			SharedResourceHolder.Resource<Executor> sharedChannelExecutorResource) {
		this.namespace = namespace;
		this.name = name;
		this.port = port;
		this.args = args;
		this.timeServiceResource = timeServiceResource;
		this.sharedChannelExecutorResource = sharedChannelExecutorResource;
	}

	@Override
	public String getServiceAuthority() {
		return "AUTHORITY";
	}

	@Override
	public void start(Listener listener) {
		System.out.println("start...");
		try {
//			client = Config.fromCluster();
			client = Config.defaultClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.listener = listener;
		refresh();
	}

	@Override
	public void shutdown() {
		System.out.println("SHUTDOWN!!!!!!!!!");
	}

	@Override
	@GuardedBy("this")
	public void refresh() {
		System.out.println("client.getAuthentication() ==== " + client.getBasePath());
		System.out.println("@@@@ refresh " + namespace + " " + name + " " + port);
		if (refreshing)
			return;
		try {
			refreshing = true;

			CoreV1Api api = new CoreV1Api(client);

			V1EndpointsList endpointsList = api.listNamespacedEndpoints(namespace, null, null, null, null, null, null,
					null, null, 10, Boolean.FALSE);

			endpointsList.getItems().stream().filter(endpoints -> name.equals(endpoints.getMetadata().getName()))
					.forEach(endpoints -> update(endpoints));

			ExecutorService es = Executors.newSingleThreadExecutor();
			es.execute(new WatchRunnable());

		} catch (ApiException e) {
			e.printStackTrace();
		} finally {
			refreshing = false;
		}
	}

	private void update(V1Endpoints endpoints) {

		List<EquivalentAddressGroup> servers = new ArrayList<>();
		if (endpoints.getSubsets() == null)
			return;

		endpoints.getSubsets().stream().forEach(subset -> {
			long matchingPorts = subset.getPorts().stream().filter(p -> {
				return p != null && p.getPort() == port;
			}).count();
			if (matchingPorts > 0) {
				subset.getAddresses().stream().map(address -> {
					System.out.println("  IP = " + address.getIp());
					System.out.println();
					return new EquivalentAddressGroup(new InetSocketAddress(address.getIp(), port));
				}).forEach(address -> {
					servers.add(address);
				});
			}
		});

		listener.onAddresses(servers, Attributes.EMPTY);
	}

	private class WatchRunnable implements Runnable {

		@Override
		public void run() {
			while (true) {
				if (watching)
					return;
				try {
					watching = true;

					CoreV1Api api = new CoreV1Api(client);

					Call call = api.listNamespacedEndpointsCall(namespace, null, null, null, null, null, null, null,
							null, 5, Boolean.TRUE, null);

					Watch<V1Endpoints> watch = Watch.createWatch(client, call,
							new TypeToken<Watch.Response<V1Endpoints>>() {
							}.getType());

					for (Response<V1Endpoints> event : watch) {
						V1Endpoints endpoints = event.object;
						if (name.equals(endpoints.getMetadata().getName())) {

							switch (event.type) {
							case "ADDED":
								System.out.println("++++ ADDED ++++");
								update(endpoints);
								break;
							case "MODIFIED":
								System.out.println("++++ MODIFIED ++++");
								update(endpoints);
								break;
							case "DELETED":
								System.out.println("++++ DELETED ++++");
								listener.onAddresses(Collections.emptyList(), Attributes.EMPTY);
								break;
							default:
							}
						}
					}
				} catch (ApiException e) {
					e.printStackTrace();
				} finally {
					watching = false;
				}
			}
		}

	}

}
