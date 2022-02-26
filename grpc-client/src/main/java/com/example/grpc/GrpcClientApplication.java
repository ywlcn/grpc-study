package com.example.grpc;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.grpc.client.utl.GrpcConfig;
import com.example.grpc.k8s.fabricclient.ClientSideLoadBalancedEchoClient;
import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloReply;
import com.example.grpc.protodefine.Helloworld.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientApplication.class, args);
		
		
		try {
			ClientSideLoadBalancedEchoClient.main(null);
		} catch (UnknownHostException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

//	public static void normalCall(final String requestBody) {
//
//		// リトライポリシーの設定
//		Map<String, Object> retryPolicy = new HashMap<>();
//		retryPolicy.put("maxAttempts", 3D);
//		retryPolicy.put("initialBackoff", "0.5s");
//		retryPolicy.put("maxBackoff", "1s");
//		retryPolicy.put("backoffMultiplier", 2D);
//		retryPolicy.put("retryableStatusCodes", Arrays.asList("UNAVAILABLE"));
//		Map<String, Object> methodConfig = new HashMap<>();
//		Map<String, Object> name = new HashMap<>();
//		name.put("service", "helloworld.Greeter");
//		methodConfig.put("name", Collections.singletonList(name));
//		methodConfig.put("retryPolicy", retryPolicy);
//		Map<String, Object> serviceConfig = new HashMap<>();
//		serviceConfig.put("methodConfig", Collections.singletonList(methodConfig));
//
//		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9092).usePlaintext().enableRetry() // リトライの有効化
//				.defaultServiceConfig(serviceConfig) // 設定の適用
//				.intercept(new HelloClientInterceptor()).build();
//
//		HelloWorldGrpc.HelloWorldBlockingStub stub = HelloWorldGrpc.newBlockingStub(channel);
//		HelloRequest request = HelloRequest.newBuilder().setName(requestBody).build();
//		HelloReply reply = stub.sayHello(request);
//
//		logger.info("normalCall from server" + reply);
//		GrpcConfig.shutDownChannel(channel);
//
//	}

	
	
}
