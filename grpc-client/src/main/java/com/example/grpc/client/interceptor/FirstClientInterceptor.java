package com.example.grpc.client.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.util.JsonFormat;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

public class FirstClientInterceptor implements ClientInterceptor {

	static Logger logger = LoggerFactory.getLogger(FirstClientInterceptor.class);

	JsonFormat.Printer pr = JsonFormat.printer().includingDefaultValueFields().omittingInsignificantWhitespace();

	@Override
	public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
			CallOptions callOptions, Channel channel) {

		return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
				channel.newCall(methodDescriptor, callOptions)) {

			@Override
			public void start(Listener<RespT> responseListener, Metadata headers) {

				logger.error("FirstClientInterceptor");
				super.start(responseListener, headers);
			}

		};
	}

}