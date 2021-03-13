package com.example.grpc.test.service;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.grpc.protodefine.DataType.CommonRequest;
import com.example.grpc.protodefine.DataType.CommonResponse;
import com.example.grpc.protodefine.EarthServiceGrpc.EarthServiceImplBase;

import io.grpc.stub.StreamObserver;

public class EarthService extends EarthServiceImplBase {
	static Logger logger = LoggerFactory.getLogger(EarthService.class);

	int port;

	public EarthService() {
		this(-1);
	}

	public EarthService(int port) {
		this.port = port;
	}

	@Override
	public void sayHello(CommonRequest request, StreamObserver<CommonResponse> responseObserver) {

		try {
			String from ="EarthService " +  InetAddress.getLocalHost().getHostAddress();

			CommonResponse reply = CommonResponse.newBuilder().setMessage("Hello " + request.getName() + "   from: " + from)
					.build();

			logger.warn(from + "Received: " + request.getName());

			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		} catch (Exception ex) {
			responseObserver.onError(ex);
		}

	}

}
