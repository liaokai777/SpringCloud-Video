package com.video.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.video.handler.NonStaticResourceHttpRequestHandler;

@RestController
public class VideoController {
	NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
	@RequestMapping("/video")
	public void getVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String url = request.getRequestURL().toString();
		 System.out.println(url);
         String path ="C:\\Users\\AS US\\Desktop\\视频\\斯洛伐克 .mp4";
         Path filePath = Paths.get(path);
         if (Files.exists(filePath)) {
             String mimeType = Files.probeContentType(filePath);
             if (!StringUtils.isEmpty(mimeType)) {
                 response.setContentType(mimeType);
             }
             request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
             nonStaticResourceHttpRequestHandler.handleRequest(request, response);
         } else {
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
         }
	}
}
