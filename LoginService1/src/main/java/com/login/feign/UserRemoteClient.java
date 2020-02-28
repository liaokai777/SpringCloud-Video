package com.login.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.login.hystrix.UserRemoteClientFallback;

//1.定义一个 Feign 的客户端，以接口形式存在  2.在 Feign 的客户端类上的 @FeignClient 注解中指定 fallback 进行回退
@FeignClient(value = "EurekaClient",fallback = UserRemoteClientFallback.class)
public interface UserRemoteClient {

    @GetMapping("/test")
    String test();
}
