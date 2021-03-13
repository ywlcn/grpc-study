package com.example.grpc.client;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.net.ssl.SSLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.grpc.client.utl.GrpcConfig;
import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloRequest;

import io.grpc.Attributes;
import io.grpc.Channel;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import io.grpc.NameResolverRegistry;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import com.example.grpc.protodefine.Helloworld.HelloReply;

public class Unary {

	public static void main(String[] args) throws Exception {
//		loadBalanceCall();
		
		tlsCall();
	}

	private static void sample() throws SSLException {
		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();

		NettyChannelBuilder channelBuilder = NettyChannelBuilder.forAddress("localhost", 5002);

//		channelBuilder.maxInboundMessageSize(0);
//		channelBuilder.maxInboundMetadataSize(0);

		ManagedChannel channel = channelBuilder.usePlaintext().build();

		HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);

//		stub.withDeadline(null);
//		stub.withDeadlineAfter(0, null);		

//		stub.withMaxInboundMessageSize(0);
//		stub.withMaxOutboundMessageSize(0);

//		stub.withInterceptors(null);

		for (int i = 0; i <= 10; i++) {
			System.out.println(stub.sayHello(request).toString());

		}

		GrpcConfig.shutDownChannel(channel);
	}

	// LoadBalanceの例
	private static void loadBalanceCall() {

		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();

		NameResolverProvider nameResolverFactory = new MultiAddressNameResolverFactory(
				new InetSocketAddress("localhost", 5001), new InetSocketAddress("localhost", 5002),
				new InetSocketAddress("localhost", 5003));

		NameResolverRegistry nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
		nameResolverRegistry.register(nameResolverFactory);

		ManagedChannel channel = ManagedChannelBuilder.forTarget("loca111lhost")
				.defaultLoadBalancingPolicy("round_robin").usePlaintext().build();

		HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);

		for (int i = 0; i <= 10; i++) {
			System.out.println(stub.sayHello(request).toString());
		}
		GrpcConfig.shutDownChannel(channel);
	}

	//　TLS通信時
	private static void tlsCall() throws SSLException {

		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();

	//   hostname はcert.pemが作成した際に、指定した情報
		ManagedChannel channel2 = NettyChannelBuilder.forAddress("localhost", 5001)
		    .sslContext(GrpcSslContexts.forClient().trustManager(new File("src/main/resources/tls/cert.pem")).build()).overrideAuthority("hostname")
		    .build();
		HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel2);		
		System.out.println(stub.sayHello(request).toString());

//		ManagedChannel channel1 = ManagedChannelBuilder.forAddress("localhost", 5001).build();
//		HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel1);
//		System.out.println(stub.sayHello(request).toString());

//		GrpcConfig.shutDownChannel(channel1);
		GrpcConfig.shutDownChannel(channel2);
	}

	


	public static class MultiAddressNameResolverFactory extends NameResolverProvider {
		final List<EquivalentAddressGroup> addresses;

		MultiAddressNameResolverFactory(SocketAddress... addresses) {
			this.addresses = Arrays.stream(addresses).map(EquivalentAddressGroup::new).collect(Collectors.toList());
		}

		public NameResolver newNameResolver(URI notUsedUri, NameResolver.Args args) {
			return new NameResolver() {
				@Override
				public String getServiceAuthority() {
					return "fakeAuthority";
				}

				public void start(Listener2 listener) {
					listener.onResult(ResolutionResult.newBuilder().setAddresses(addresses)
							.setAttributes(Attributes.EMPTY).build());
				}

				public void shutdown() {
				}
			};
		}

		@Override
		public String getDefaultScheme() {
			return "multiaddress";
		}

		@Override
		protected boolean isAvailable() {
			return true;
		}

		@Override
		protected int priority() {
			return 0;
		}
	}
}
