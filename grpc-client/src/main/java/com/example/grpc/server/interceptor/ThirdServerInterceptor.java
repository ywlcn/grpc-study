package com.example.grpc.server.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.util.JsonFormat;

import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class ThirdServerInterceptor implements ServerInterceptor {
	static Logger logger = LoggerFactory.getLogger(ThirdServerInterceptor.class);

	JsonFormat.Printer pr = JsonFormat.printer().includingDefaultValueFields().omittingInsignificantWhitespace();

	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata requestHeaders,
			ServerCallHandler<ReqT, RespT> next) {

		ServerCall.Listener<ReqT> delegate = next.startCall(call, requestHeaders);

		return new InnerServerCalllListener<ReqT>(delegate);

	}

	class InnerServerCalllListener<T> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<T> {

		protected InnerServerCalllListener(io.grpc.ServerCall.Listener<T> delegate) {
			super(delegate);
		}

		@Override
		public void onMessage(T message) {
			logger.error("ServerInterceptor   onMessage");
			super.onMessage(message);
		}

	}
}
