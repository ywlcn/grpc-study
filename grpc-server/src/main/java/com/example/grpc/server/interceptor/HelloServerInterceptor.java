package com.example.grpc.server.interceptor;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.ForwardingServerCall;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.ClientCall.Listener;

import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.DemoServer;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

//@GRpcGlobalInterceptor
public class HelloServerInterceptor implements ServerInterceptor {
	static Logger logger = LoggerFactory.getLogger(HelloServerInterceptor.class);

	JsonFormat.Printer pr = JsonFormat.printer().includingDefaultValueFields().omittingInsignificantWhitespace();

	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata requestHeaders,
			ServerCallHandler<ReqT, RespT> next) {

//		return next.startCall(call, headers);
		ServerCall<ReqT, RespT> wrapperCall = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {

			@Override
			public void sendMessage(RespT message) {
				logger.info("ServerInterceptor   sendMessage ---start");
				super.sendMessage(message);
				DemoServer.sleep();
				logger.info("ServerInterceptor   sendMessage ---end");
			}

			@Override
			public void request(int numMessages) {
				logger.info("ServerInterceptor   request ---start " + numMessages);
				super.request(numMessages);
				DemoServer.sleep();
				logger.info("ServerInterceptor   request ---end");
			}

			@Override
			public void sendHeaders(Metadata headers) {
				logger.info("ServerInterceptor   sendHeaders ---start");
				super.sendHeaders(headers);
				DemoServer.sleep();
				logger.info("ServerInterceptor   sendHeaders ---end");
			}

			@Override
			public boolean isReady() {
				return delegate().isReady();
			}

			@Override
			public void close(Status status, Metadata trailers) {
				logger.info("ServerInterceptor   close ---start");
				super.close(status, trailers);
				DemoServer.sleep();
				logger.info("ServerInterceptor   close ---end");
			}

			@Override
			public boolean isCancelled() {
				return delegate().isCancelled();
			}

		};

		ServerCall.Listener<ReqT> delegate = next.startCall(wrapperCall, requestHeaders);

		return new InnerServerCalllListener<ReqT>(delegate);

	}

}

class InnerServerCalllListener<T> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<T> {

	static Logger logger = LoggerFactory.getLogger(InnerServerCalllListener.class);

	protected InnerServerCalllListener(io.grpc.ServerCall.Listener<T> delegate) {
		super(delegate);
	}

	@Override
	public void onHalfClose() {
		logger.info("ServerInterceptor   onHalfClose ---start");
		super.onHalfClose();
		DemoServer.sleep();
		logger.info("ServerInterceptor   onHalfClose ---end");
	}

	@Override
	public void onCancel() {
		logger.info("ServerInterceptor   onCancel ---start");
		super.onCancel();
		DemoServer.sleep();
		logger.info("ServerInterceptor   onCancel ---end");
	}

	@Override
	public void onComplete() {
		logger.info("ServerInterceptor   onComplete ---start");
		super.onComplete();
		DemoServer.sleep();
		logger.info("ServerInterceptor   onComplete ---end");
	}

	@Override
	public void onReady() {
		logger.info("ServerInterceptor   onReady ---start");
		super.onReady();
		DemoServer.sleep();
		logger.info("ServerInterceptor   onReady ---end");
	}

	@Override
	public void onMessage(T message) {
		logger.info("ServerInterceptor   onMessage ---start");
		super.onMessage(message);
		logger.info("ServerInterceptor   onMessage ---end");
	}
	
//	@Override
//	public void onHead(T message) {
//		logger.info("ServerInterceptor   onMessage ---start");
//		super.onMessage(message);
//		logger.info("ServerInterceptor   onMessage ---end");
//	}

}
