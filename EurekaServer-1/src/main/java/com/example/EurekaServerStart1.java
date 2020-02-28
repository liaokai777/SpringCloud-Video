package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//表示开启 Eureka Server
@EnableEurekaServer
public class EurekaServerStart1 {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerStart1.class, args);
	}
}
