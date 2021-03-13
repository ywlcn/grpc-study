package com.example.grpc.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.grpc.protodefine.Helloworld.HelloReply;
import com.example.grpc.protodefine.Helloworld.HelloRequest;

public class ServerStreaming {

	public static void main(String[] args) throws InterruptedException {
		HelloRequest request = HelloRequest.newBuilder().setName("Tom").build();

		BaseRun baseRun = new BaseRun();

		Iterator<HelloReply> replies = baseRun.blockingStub().sayHelloServerStreaming(request);
		List<HelloReply> response = new ArrayList<>();
		while (replies.hasNext()) {
			response.add(replies.next());
		}
		System.out.println(response.toString()); // [message: "Hello Tom", message: "Hello Tom", message: "Hello Tom"]
	}

}
