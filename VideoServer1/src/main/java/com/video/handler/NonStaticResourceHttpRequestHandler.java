package com.video.handler;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class NonStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {
	public final static String ATTR_FILE = "VIDEO";
	@Override
	protected Resource getResource(HttpServletRequest request) throws IOException {
		final Path filePath = (Path) request.getAttribute(ATTR_FILE);
		 return new FileSystemResource(filePath);
	}
}
