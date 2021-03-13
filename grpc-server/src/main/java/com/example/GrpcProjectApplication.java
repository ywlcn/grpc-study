package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


@SpringBootApplication

public class GrpcProjectApplication {
	static Logger logger = LoggerFactory.getLogger(GrpcProjectApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(GrpcProjectApplication.class, args);
	}

	@Bean
	public Mapper mapper() {
//        return new BeanMappingBuilder() {
//            @Override
//            protected void configure() {
//                mapping(type(TodoForm.class), type(Todo.class))
//                    .fields(field("todiTitle"), field("title"));
//            }
//        };

		return new DozerBeanMapper();

	}

}
