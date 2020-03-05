package com.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	 @RequestMapping("/index")
	 public String toLoginPage() {
		 logger.info("访问一机主页");
	     return "index";
	 }
}
