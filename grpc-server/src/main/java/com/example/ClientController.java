package com.example;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.grpc.client.utl.GrpcConfig;
import com.example.grpc.protodefine.HelloWorldGrpc;
import com.example.grpc.protodefine.Helloworld.HelloRequest;
import com.example.grpc.protodefine.Helloworld.PerforData;
import com.sample.dto.UserInfo;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class ClientController {

	@Autowired
	GrpcConfig grpcConfig;

	@Autowired
	Mapper mapper;

	private static WebClient client = WebClient.create("http://localhost:8080");

	@RequestMapping("/rest")
	@ResponseBody
	public UserInfo rest() {
		// Jmeterから受信して、WebClient通信を行う

		
//	    HttpClient httpClient = HttpClient.create()
//	            .tcpConfiguration(client ->
//	                    client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//	                    .doOnConnected(conn -> conn
//	                            .addHandlerLast(new ReadTimeoutHandler(10))
//	                            .addHandlerLast(new WriteTimeoutHandler(10))));
//	     
//	    ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);     
//	 
//	    return WebClient.builder()
//	            .baseUrl("http://localhost:3000")
//	            .clientConnector(connector)
//	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//	            .build();
	    
	    
		UserInfo ui = new UserInfo();
		ui.setName("Tom");
		UserInfo result = client.post().uri("/performancetest").body(Mono.just(ui), UserInfo.class).retrieve()
				.bodyToMono(UserInfo.class).block();

		return result;
	}

	@RequestMapping("/grpc")
	@ResponseBody
	public UserInfo grpc() {
		// Jmeterから受信して、gRPC通信を行う

		PerforData request = PerforData.newBuilder().setName("Tom").build();

		PerforData res = HelloWorldGrpc.newBlockingStub(grpcConfig.getDefaultChannel()).sayPerformacne(request);

		UserInfo ret = mapper.map(res, UserInfo.class);

		return ret;
	}

}
