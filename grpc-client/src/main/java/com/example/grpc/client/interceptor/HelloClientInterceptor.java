package com.example.grpc.client.interceptor;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.util.JsonFormat;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientCall.Listener;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

public class HelloClientInterceptor implements ClientInterceptor {

	static Logger logger = LoggerFactory.getLogger(HelloClientInterceptor.class);

	JsonFormat.Printer pr = JsonFormat.printer().includingDefaultValueFields().omittingInsignificantWhitespace();

	@Override
	public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
			CallOptions callOptions, Channel channel) {

		return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
				channel.newCall(methodDescriptor, callOptions)) {

			@Override
			public void sendMessage(ReqT message) {
//				try {
//					logger.info("ClientInterceptor   sendMessage:" + methodDescriptor.getFullMethodName() + " | "
//							+ pr.print((MessageOrBuilder) message).toString());
//				} catch (InvalidProtocolBufferException e) {
//					logger.error(e.getMessage());
//				}
				logger.info("ClientInterceptor   sendMessage ---start");
				super.sendMessage(message);
				logger.info("ClientInterceptor   sendMessage ---end");
			}

			@Override
			public void start(Listener<RespT> responseListener, Metadata headers) {
//				System.out.println(HelloClientInterceptor.class.getSimpleName());

				ClientCall.Listener<RespT> listener = new InnerClientCallListener<RespT>(responseListener);
				super.start(listener, headers);
			}

			@Override
			public void request(int numMessages) {
				logger.info("ClientInterceptor   request ---start   " + numMessages);
				super.request(numMessages);
				logger.info("ClientInterceptor   request ---end");
			}

			@Override
			public void halfClose() {
				logger.info("ClientInterceptor   halfClose ---start");
				super.halfClose();
				logger.info("ClientInterceptor   halfClose ---end");
			}

			@Override
			public void cancel(@Nullable String message, @Nullable Throwable cause) {
				logger.info("ClientInterceptor   cancel ---start");
				super.cancel(message, cause);
				logger.info("ClientInterceptor   cancel ---end");
			}

		};
	}
	
	class InnerClientCallListener<T> extends ForwardingClientCallListener<T> {
		
		final Listener<T> responseListener ;
		
		public InnerClientCallListener(Listener<T> responseListener ) {
			this.responseListener = responseListener;
		}

		@Override
		protected Listener<T> delegate() {
			return this.responseListener;
		}
		
		
		@Override
		public void onReady() {
			logger.info("ClientInterceptor   onReady ---start");
			super.onReady();
			logger.info("ClientInterceptor   onReady ---end");
		}

		@Override
		public void onHeaders(Metadata headers) {
			logger.info("ClientInterceptor   onHeaders ---start");
			super.onHeaders(headers);
			logger.info("ClientInterceptor   onHeaders ---end");
//			String encoding = headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
//			if (encoding == null) {
//				throw new RuntimeException("No compression selected!");
//			}
//			logger.info("ClientInterceptor   onHeaders" + headers.toString());
		}

		@Override
		public void onClose(Status status, Metadata trailers) {
//			if (!status.isOk()) {
//				throw status.asRuntimeException();
//			}
			
			logger.info("ClientInterceptor   onClose ---start");
			super.onClose(status,trailers);
			logger.info("ClientInterceptor   onClose ---end");
			
//			logger.info("ClientInterceptor   onClose" + status.toString() + "   |   " + trailers.toString());
		}
		
		@Override
		public void onMessage(T message) {
			
			logger.info("ClientInterceptor   onMessage ---start");
			super.onMessage(message);
			logger.info("ClientInterceptor   onMessage ---end");
									
//			try {
//				logger.info("ClientInterceptor   onMessage:" + pr.print((MessageOrBuilder) message).toString());
//			} catch (InvalidProtocolBufferException e) {
//				e.printStackTrace();
//			}
		}
		
		
	}
	

}