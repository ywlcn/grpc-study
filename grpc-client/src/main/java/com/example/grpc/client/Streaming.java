package com.example.grpc.client;

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

public class Streaming {

	public static void main(String[] args) throws InterruptedException {

		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();
		CountDownLatch finishLatch = new CountDownLatch(1);
		List<HelloReply> response = new ArrayList<>();

		BaseRun baseRun = new BaseRun();

		StreamObserver<HelloRequest> streamObserver = baseRun.stub()
				.sayHelloStreaming(new StreamObserver<HelloReply>() {
					@Override
					public void onNext(HelloReply reply) {
						System.out.println(reply.toString());
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
		//System.out.println(response.toString()); // [message: "Hello Tom" , message: "Hello Tom" , message: "Hello Tom"
													// ,
		// message: "Hello Tom" , message: "Hello Tom" , message: "Hello Tom" , message:
		// "Hello Tom" , message: "Hello Tom" , message: "Hello Tom" ]
	}

}
