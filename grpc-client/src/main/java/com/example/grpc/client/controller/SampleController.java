package com.example.grpc.client.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.grpc.client.utl.GrpcConfig;
import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloRequest;

import io.grpc.stub.StreamObserver;

import com.example.grpc.protodefine.Helloworld.HelloReply;

@RequestMapping("/sample")
@Controller("SampleController")
@SessionAttributes("")
public class SampleController {

	@Autowired
	GrpcConfig grpcConfig;

	

	
	
	private String unary() {
		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();
		return blockingStub().sayHello(request).toString(); // message: "Hello Tom"
	}

	private String serverStreaming() {
		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();
		Iterator<HelloReply> replies = blockingStub().sayHelloServerStreaming(request);
		List<HelloReply> response = new ArrayList<>();
		while (replies.hasNext()) {
			response.add(replies.next());
		}
		return response.toString(); // [message: "Hello Tom", message: "Hello Tom", message: "Hello Tom"]
	}

	private String clientStreaming() throws Exception {
		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();
		CountDownLatch finishLatch = new CountDownLatch(1);
		List<HelloReply> response = new ArrayList<>();
		StreamObserver<HelloRequest> streamObserver = stub().sayHelloClientStreaming(new StreamObserver<HelloReply>() {
			@Override
			public void onNext(HelloReply reply) {
				response.add(reply);
			}

			@Override
			public void onError(Throwable t) {
				// ...
			}

			@Override
			public void onCompleted() {
				finishLatch.countDown();
			}
		});
		streamObserver.onNext(request);
		streamObserver.onNext(request);
		streamObserver.onNext(request);
		streamObserver.onCompleted();
		finishLatch.await(10, TimeUnit.SECONDS);
		return response.toString(); // message: "Hello [Tom, Tom, Tom]"
	}

	private String streaming() throws Exception {
		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();
		CountDownLatch finishLatch = new CountDownLatch(1);
		List<HelloReply> response = new ArrayList<>();
		StreamObserver<HelloRequest> streamObserver = stub()
				.sayHelloStreaming(new StreamObserver<HelloReply>() {
					@Override
					public void onNext(HelloReply reply) {
						response.add(reply);
					}

					@Override
					public void onError(Throwable t) {
						// ...
					}

					@Override
					public void onCompleted() {
						finishLatch.countDown();
					}
				});
		streamObserver.onNext(request);
		streamObserver.onNext(request);
		streamObserver.onNext(request);
		streamObserver.onCompleted();
		finishLatch.await(10, TimeUnit.SECONDS);
		return response.toString(); // [message: "Hello Tom" , message: "Hello Tom" , message: "Hello Tom" ,
									// message: "Hello Tom" , message: "Hello Tom" , message: "Hello Tom" , message:
									// "Hello Tom" , message: "Hello Tom" , message: "Hello Tom" ]
	}

	private HelloWorldGrpc.HelloWorldBlockingStub blockingStub() {
		return HelloWorldGrpc.newBlockingStub(grpcConfig.getDefaultChannel());
	}

	private HelloWorldGrpc.HelloWorldStub stub() {
		return HelloWorldGrpc.newStub(grpcConfig.getDefaultChannel());
	}

}
