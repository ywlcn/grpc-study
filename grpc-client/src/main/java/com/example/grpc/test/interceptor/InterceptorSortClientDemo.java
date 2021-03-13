package com.example.grpc.test.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.example.grpc.client.interceptor.FirstClientInterceptor;
import com.example.grpc.client.interceptor.HelloClientInterceptor;
import com.example.grpc.client.interceptor.SecondClientInterceptor;
import com.example.grpc.client.interceptor.ThirdClientInterceptor;
import com.example.grpc.client.utl.GrpcConfig;
import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloReply;
import com.example.grpc.protodefine.Helloworld.HelloRequest;
import com.example.grpc.server.interceptor.FirstServerInterceptor;
import com.google.common.util.concurrent.Uninterruptibles;

import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ClientCall.Listener;
import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties.Jetty.Threads;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

//@SpringBootApplication
public class InterceptorSortClientDemo {

	static Logger logger = LoggerFactory.getLogger(InterceptorSortClientDemo.class);

	private static WebClient client = WebClient.create("http://localhost:8080");

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(InterceptorSortClientDemo.class, args);


		
		
//		ClientStreaming.main(null);

		normalCall("normalCall");

//		detailCall("detailCall");
	}

	private static void normalCall(final String requestBody) {

		// リトライポリシーの設定
		Map<String, Object> retryPolicy = new HashMap<>();
		retryPolicy.put("maxAttempts", 3D);
		retryPolicy.put("initialBackoff", "0.5s");
		retryPolicy.put("maxBackoff", "1s");
		retryPolicy.put("backoffMultiplier", 2D);
		retryPolicy.put("retryableStatusCodes", Arrays.asList("UNAVAILABLE"));
		Map<String, Object> methodConfig = new HashMap<>();
		Map<String, Object> name = new HashMap<>();
		name.put("service", "helloworld.Greeter");
		methodConfig.put("name", Collections.singletonList(name));
		methodConfig.put("retryPolicy", retryPolicy);
		Map<String, Object> serviceConfig = new HashMap<>();
		serviceConfig.put("methodConfig", Collections.singletonList(methodConfig));

		
		List<ClientInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new FirstClientInterceptor());
		interceptors.add(new SecondClientInterceptor());
		interceptors.add(new ThirdClientInterceptor());
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9092).usePlaintext().enableRetry() // リトライの有効化
				.defaultServiceConfig(serviceConfig) // 設定の適用
				.intercept(interceptors).build();

		HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);
		HelloRequest request = HelloRequest.newBuilder().setName(requestBody).build();
		
		logger.info("normalCall from server" + stub.sayHello(request));

//		logger.info("normalCall from server" + stub.sayHello(request));
//		logger.info("normalCall from server" + stub.sayHello(request));
//		logger.info("normalCall from server" + stub.sayHello(request));
//		logger.info("normalCall from server" + stub.sayHello(request));
//		logger.info("normalCall from server" + stub.sayHello(request));
//		logger.info("normalCall from server" + stub.sayHello(request));
		GrpcConfig.shutDownChannel(channel);

	}

	private static void detailCall(final String name) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9092).usePlaintext().enableRetry()
				.intercept(new HelloClientInterceptor()).build();

		final ClientCall<HelloRequest, HelloReply> call = channel.newCall(HelloWorldGrpc.getSayHelloMethod(),
				CallOptions.DEFAULT);

		final CountDownLatch latch = new CountDownLatch(1);

		call.start(new Listener<HelloReply>() {
			@Override
			public void onHeaders(Metadata headers) {
				super.onHeaders(headers);
				String encoding = headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
				if (encoding == null) {
					throw new RuntimeException("No compression selected!");
				}
				logger.info("onHeaders" + headers.toString());
			}

			@Override
			public void onMessage(HelloReply message) {
				super.onMessage(message);
				logger.info("onMessage" + message.toString());
			}

			@Override
			public void onClose(Status status, Metadata trailers) {
				latch.countDown();
				if (!status.isOk()) {
					throw status.asRuntimeException();
				}
				logger.info("onClose" + status.toString() + "   |   " + trailers.toString());
			}
		}, new Metadata());

		call.setMessageCompression(true);
		call.sendMessage(HelloRequest.newBuilder().setName(name).build());
		call.request(1);
		call.halfClose();

		try {
			latch.await();

			GrpcConfig.shutDownChannel(channel);

//			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		Uninterruptibles.awaitUninterruptibly(latch, 100, TimeUnit.SECONDS);
	}

}