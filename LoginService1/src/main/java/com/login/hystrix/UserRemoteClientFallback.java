package com.login.hystrix;

import org.springframework.stereotype.Component;

import com.login.feign.UserRemoteClient;

//fallback回退实现类 
@Component
public class UserRemoteClientFallback implements UserRemoteClient {

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "fallback fail";
	}

}
