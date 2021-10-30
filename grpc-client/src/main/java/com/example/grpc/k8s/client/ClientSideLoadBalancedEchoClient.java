package com.example.grpc.k8s.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverRegistry;
import io.grpc.StatusRuntimeException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloReply;
import com.example.grpc.protodefine.Helloworld.HelloRequest;

public class ClientSideLoadBalancedEchoClient {
	private static int THREADS = 4;
	private static Random RANDOM = new Random();

	public static void run(int count) throws UnknownHostException, InterruptedException {

		// "kubernetes:///default/echo-server/9092"
//		String target = System.getenv("ECHO_SERVICE_TARGET");
		String target = "kubernetes:///default/echo-server/9092";
		if (target == null || target.isEmpty()) {
			target = "localhost:8080";
		}

		NameResolverRegistry nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
		nameResolverRegistry.register(new KubernetesNameResolverProvider());

		final ManagedChannel channel = ManagedChannelBuilder.forTarget(target).defaultLoadBalancingPolicy("round_robin")
				.usePlaintext().build();

		final String self = InetAddress.getLocalHost().getHostName();

		ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
		for (int i = 0; i < THREADS; i++) {
			HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);
			executorService.submit(() -> {
				int j = 0;
				while (j <= count) {
					HelloReply response = stub.sayHello(
							HelloRequest.newBuilder().setName(self + ": " + Thread.currentThread().getName()).build());
					System.out.println(response.getMessage() + " echoed");
					j++;
					try {
						Thread.sleep(RANDOM.nextInt(700));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public static void main(String[] args) throws InterruptedException, UnknownHostException {
//		String target = System.getenv("ECHO_SERVICE_TARGET");

//		String target = "kubernetes:///default/hello-minikube/80";

		String target = null; // "kubernetes:///default/echo-server/9092";

		if (args != null && args.length > 0) {
			target = "kubernetes:///default/hello-minikube/80";
			target = args[0];
		} else {
//			target = "localhost:8080";30749 30388
			target = "192.168.56.101:30749";
		}

		ManagedChannel channel = null;
		if (target.startsWith("kubernetes:///")) {

			NameResolverRegistry nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
			nameResolverRegistry.register(new KubernetesNameResolverProvider());

//		    final ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
//		        .nameResolverFactory(new KubernetesNameResolverProvider())  // this is on by default
//		        .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance())
//		        .usePlaintext(true)
//		        .build();
			channel = ManagedChannelBuilder.forTarget(target).defaultLoadBalancingPolicy("round_robin").usePlaintext()
					.build();

		} else {
			channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
		}

		accessServer(channel);

	}

	private static void accessServer(ManagedChannel channel) throws UnknownHostException {

		final String self = InetAddress.getLocalHost().getHostName();

		ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
		for (int i = 0; i < THREADS; i++) {
			HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);

//			HelloWorldGrpc.HelloWorldBlockingStub stub1 = HelloWorldGrpc.newFutureStub(channel)

			executorService.submit(() -> {
				while (true) {

					try {
						Thread.sleep(1000);

						HelloReply response = stub.sayHello(HelloRequest.newBuilder()
								.setName(self + ": " + Thread.currentThread().getName()).build());
						System.out.println(response.getMessage() + " echoed");

					} catch (StatusRuntimeException ex) {

						System.err.println(ex.getCause().toString());

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			});
		}

	}

}
