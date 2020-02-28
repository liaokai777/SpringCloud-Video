package com.talk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.talk.websocket.WebsocketServer;

@SpringBootApplication
@EnableDiscoveryClient
public class TalkServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkServerApplication.class, args);
		WebsocketServer.startServer();
	}

}
