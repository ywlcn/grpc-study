package com.example.grpc.client.utl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.example.grpc.client.interceptor.HelloClientInterceptor;

import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class GrpcConfig implements DisposableBean {

	@Value("${test.grpc.hostname}")
	private String hostname;

	@Value("${test.grpc.port}")
	private int port;

	private ManagedChannel channel = null;

	public ManagedChannel getDefaultChannel() {
		return getDefaultChannel(null);
	}

	public ManagedChannel getDefaultChannel(ClientInterceptor clientInterceptor) {
		if (this.channel == null || this.channel.isShutdown() || this.channel.isTerminated()) {
			this.channel = createChannel(hostname, port, clientInterceptor);
		}
		return this.channel;
	}

	/**
	 * 新しい使え捨てなManagedChannel作成する
	 * 
	 * @param hostname
	 * @param port
	 * @param clientInterceptor
	 * @return
	 */
	public ManagedChannel createChannel(String hostname, int port, ClientInterceptor clientInterceptor) {
		ManagedChannelBuilder<?> managerdChannelBuilder = null;

		if (clientInterceptor == null) {
			managerdChannelBuilder = ManagedChannelBuilder.forAddress(hostname, port);
		} else {
			managerdChannelBuilder = ManagedChannelBuilder.forAddress(hostname, port).intercept(clientInterceptor);
		}
//		ManagedChannel channel = managerdChannelBuilder.intercept(new HelloClientInterceptor()).usePlaintext().enableRetry().build();

		ManagedChannel channel = managerdChannelBuilder.intercept().usePlaintext().enableRetry().build();
		return channel;
	}

	@Override
	public void destroy() throws Exception {

		shutDownChannel(this.channel);

		this.channel = null;
	}

	public static void shutDownChannel(ManagedChannel channel) {

		if (channel == null) {
			return;
		}

		if (channel.isShutdown() || channel.isTerminated()) {
			return;
		}

		try {
			channel.shutdown();
			if (channel.awaitTermination(10, TimeUnit.SECONDS) == false) {
				channel.shutdownNow();
			}
			if (channel.awaitTermination(10, TimeUnit.SECONDS) == false) {
				System.err.println("チャンネルがシャットダウンできませんでした！");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
