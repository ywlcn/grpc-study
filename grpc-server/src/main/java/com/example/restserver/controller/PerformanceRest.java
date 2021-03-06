package com.example.restserver.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.sample.dto.UserInfo;

@RestController
public class PerformanceRest {

	static Logger logger = LoggerFactory.getLogger(PerformanceRest.class);

	@Autowired
	Mapper mapper;

	@PostMapping("/performancetest")
	@ResponseBody
	public UserInfo performancetest(@RequestBody UserInfo request) {

		UserInfo reply = new UserInfo();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reply.setAddress(request.getAddress());
		reply.setId(request.getId());
		reply.setName(request.getName() + "restserver");
		reply.setSex(request.getSex());
		reply.setTel(request.getTel());

		return reply;

	}

	@GetMapping("/systeminfo")
	@ResponseBody
	// @Produces( {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON} ) doesn't work
	// either.
//	@Produces(MediaType.TEXT_PLAIN)
	public ResponseEntity<String> systeminfo() {

		StringBuilder stb = new StringBuilder();

		final double mbunit = 1024.0 * 1024;

		// ??????MemoryMXBean
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

		// ????????????????????????
		stb.append("heapMemoryUsage: " + memoryMXBean.getHeapMemoryUsage().toString() + "\n");

		// ???????????????????????????
		stb.append("nonHeapMemoryUsage: " + memoryMXBean.getNonHeapMemoryUsage().toString() + System.lineSeparator());

		// ??????JVM?????????????????????(M)
		double total = (Runtime.getRuntime().totalMemory()) / mbunit;
		stb.append("??????JVM?????????????????????(M): " + total + System.lineSeparator());

		// JVM????????????????????????(M)
		double max = (Runtime.getRuntime().maxMemory()) / mbunit;
		stb.append("JVM????????????????????????(M): " + max + System.lineSeparator());

		// JVM????????????(M)
		double free = (Runtime.getRuntime().freeMemory()) / mbunit;
		stb.append("JVM????????????(M): " + free + System.lineSeparator());

		// ??????????????????(M)
		double mayuse = (max - total + free);
		stb.append("??????????????????(M): " + mayuse + System.lineSeparator());

		// ??????????????????(M)
		double used = (total - free);
		stb.append("??????????????????(M): " + used + System.lineSeparator());
		
		HttpHeaders hd = new HttpHeaders();
		hd.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);
		hd.add(HttpHeaders.CONTENT_ENCODING, "UTF-8");

		return new ResponseEntity<String>(stb.toString() ,hd, HttpStatus.OK);

	}

}
