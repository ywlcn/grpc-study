package com.example;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.autoconfigure.web.ServerProperties.Jetty.Threads;

import com.example.grpc.server.HelloWorlController;
import com.example.grpc.server.interceptor.HelloServerInterceptor;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
//import io.grpc.internal.ServerImplBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;

public class DemoServer {

	public static void main(String[] args) throws Exception {
		
		startTLSServer(5001);
		
//		(new RunnableDemo(5001)).start();
//		(new RunnableDemo(5002)).start();
//		(new RunnableDemo(5003)).start();
	}

	private static void simpleStartServer(int port) {
		Server server = null;
		ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
		serverBuilder.addService(new HelloWorlController(port));
		server = serverBuilder.build();
		try {
			server.start();
			server.awaitTermination();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void startTLSServer(int port) throws IOException, InterruptedException {
		Server server = null; // = ServerBuilder.forPort(6565).addService(new HelloWorlController()).build();

		ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);

		
		serverBuilder.useTransportSecurity(new File("src/main/resources/tls/cert.pem"), new File("src/main/resources/tls/key.pem"));


		
		
		HelloWorlController service = new HelloWorlController();

		// インターセプター 独自のサービスに対する
		List<ServerInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HelloServerInterceptor());
		serverBuilder.addService(ServerInterceptors.intercept(service.bindService(), interceptors));

		server = serverBuilder.build();
		server.start();
		server.awaitTermination();

	}

	private void startServer() throws IOException, InterruptedException {
		Server server = null; // = ServerBuilder.forPort(6565).addService(new HelloWorlController()).build();

		ServerBuilder<?> serverBuilder = ServerBuilder.forPort(9092);

		// メッセージサイズ制限 デフォルトは4MB
		serverBuilder.maxInboundMessageSize(1);
		// Metaデータ制限
		serverBuilder.maxInboundMetadataSize(1);

//		File file = new File("");
//		serverBuilder.useTransportSecurity(file, file);

		// インターセプター 全部のサービスに対する
		serverBuilder.intercept(new HelloServerInterceptor());

		HelloWorlController service = new HelloWorlController();

		// インターセプター 独自のサービスに対する
		List<ServerInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HelloServerInterceptor());
		serverBuilder.addService(ServerInterceptors.intercept(service.bindService(), interceptors));

		server = serverBuilder.build();
		server.start();
		server.awaitTermination();

	}

	static class RunnableDemo implements Runnable {
		private Thread t;
		private int port;

		RunnableDemo(int port) {
			this.port = port;
		}

		public void run() {
			simpleStartServer(port);
		}

		public void start() {
			if (t == null) {
				t = new Thread(this);
				t.start();
			}
		}
	}

	public static void sleep() {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}