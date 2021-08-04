package com.springwebfluxproject.springwebfluxproject;


import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxProjectApplication {
//	@Value("classpath:/schema.sql")
//	private Resource resource;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxProjectApplication.class, args);
	}

}
