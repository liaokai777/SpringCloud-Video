package com.login.conf;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration//标明该类使用Spring基于Java的配置
public class BeanConfiguration {
	@LoadBalanced
    @Bean//springboot默认创建的bean是单例
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
