package com.example.grpc.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloReply;
import com.example.grpc.protodefine.Helloworld.HelloRequest;
import com.example.grpc.protodefine.Helloworld.PerforData;

import io.grpc.stub.StreamObserver;

@GRpcService
public class HelloWorlController extends HelloWorldGrpc.HelloWorldImplBase {
	static Logger logger = LoggerFactory.getLogger(HelloWorlController.class);

	@Autowired
	Mapper mapper;

	int port;

	public HelloWorlController() {
		this(-1);
	}

	public HelloWorlController(int port) {
		this.port = port;
	}

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

		try {
			String from = InetAddress.getLocalHost().getHostAddress();

			HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName() + "   from: " + from)
					.build();

			logger.warn(from + "Received: " + request.getName());

			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		} catch (Exception ex) {
			responseObserver.onError(ex);
		}

	}

	@Override
	public void sayPerformacne(PerforData request, io.grpc.stub.StreamObserver<PerforData> responseObserver) {

		try {
			PerforData reply = PerforData.newBuilder().setAddress(request.getAddress()).setId(request.getId())
					.setName(request.getName() + "grpcserver").setSex(request.getSex()).setTel(request.getTel())
					.build();
			Thread.sleep(10);
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		} catch (Exception ex) {
			responseObserver.onError(ex);
		}

	}

	@Override
	public void sayHelloServerStreaming(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onNext(reply);
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<HelloRequest> sayHelloClientStreaming(StreamObserver<HelloReply> responseObserver) {
		List<String> requests = new ArrayList<>();

		return new StreamObserver<HelloRequest>() {

			@Override
			public void onNext(HelloRequest request) {
				requests.add(request.getName());
			}

			@Override
			public void onError(Throwable t) {
				// ...
			}

			@Override
			public void onCompleted() {
				HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + requests.toString()).build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}

		};

	}

	@Override
	public StreamObserver<HelloRequest> sayHelloStreaming(StreamObserver<HelloReply> responseObserver) {

		return new StreamObserver<HelloRequest>() {

			@Override
			public void onNext(HelloRequest request) {
				HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
				responseObserver.onNext(reply);
				responseObserver.onNext(reply);
				responseObserver.onNext(reply);
			}

			@Override
			public void onError(Throwable t) {
				// ...
			}

			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
			}

		};

	}
}
