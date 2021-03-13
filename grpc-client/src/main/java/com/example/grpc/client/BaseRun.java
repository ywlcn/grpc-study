package com.example.grpc.client;

import com.example.grpc.client.utl.GrpcConfig;
import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloReply;
import com.example.grpc.protodefine.Helloworld.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BaseRun {

	public GrpcConfig grpcConfig;
	
	public BaseRun() {		
		grpcConfig = new GrpcConfig();
		grpcConfig.setHostname("localhost");
		grpcConfig.setPort(9092);		
		grpcConfig.getDefaultChannel();
	}
	
    
	public HelloWorldGrpc.HelloWorldBlockingStub blockingStub() {
		
		
		return HelloWorldGrpc.newBlockingStub(grpcConfig.getDefaultChannel());
	}

	public HelloWorldGrpc.HelloWorldStub stub() {
		return HelloWorldGrpc.newStub(grpcConfig.getDefaultChannel());
	}
	
	
}
