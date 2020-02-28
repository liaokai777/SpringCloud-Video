package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.login.feign.UserRemoteClient;



@RestController
public class UserClientController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRemoteClient userRemoteClient;
	
	@GetMapping("/calltest/{name}")
	public String getData2(@PathVariable("name") String name) {
		System.out.println("/call/data2/{name}");
		//return restTemplate.getForObject("http://localhost:8083/test",String.class);
	    //return restTemplate.getForObject( "http://EurekaClient/test", String.class, name);
	    return userRemoteClient.test();// 采用Feign 来调用 /user/hello 接口 请求字符串类型
	}
}
