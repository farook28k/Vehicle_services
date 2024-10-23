  package com.vechical.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class VechicalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VechicalServiceApplication.class, args);
	}

}
