package com.login.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.login.feign.UserRemoteClient;

import feign.hystrix.FallbackFactory;

@Component
public class UserRemoteClientFallbackFactory implements FallbackFactory<UserRemoteClient> {
    private Logger logger = LoggerFactory.getLogger(UserRemoteClientFallbackFactory.class);

    @Override
    public UserRemoteClient create(final Throwable cause) {
        logger.error("UserRemoteClient回退：", cause);
        return new UserRemoteClient() {
			@Override
			public String test() {
				// TODO Auto-generated method stub
				return "FallbackFactory fail";
			}
        };
    }
}
