package com.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.video.websocket.WebsocketServer;

@SpringBootApplication
public class VideoServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(VideoServer1Application.class, args);
		WebsocketServer.startServer();
	}

}
