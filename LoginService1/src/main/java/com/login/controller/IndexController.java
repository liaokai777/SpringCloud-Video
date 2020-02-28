package com.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.pojo.User;
import com.login.util.RedisUtil;
@Controller
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	RedisUtil redisUtil;
	 @RequestMapping("/index")
	 public String toLoginPage() {
		/* logger.info("访问第一台登录服务");
		 User user = new User();
		 long num = redisUtil.incr("num", 1L);
		 user.setIp("访问第一台登录服务"+num);*/
	     return "index";
	 }
}
