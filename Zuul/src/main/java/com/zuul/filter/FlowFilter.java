package com.zuul.filter;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.micrometer.core.instrument.util.JsonUtils;

public class FlowFilter extends ZuulFilter {
	@Autowired
	private RedisTemplate<String, Object> redistemplate;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		String url = ctx.getRequest().getRequestURL().toString();
		if (!redistemplate.hasKey(url)) {
			System.out.println("流量过滤器" + url);
			// 设置一秒钟超时
			redistemplate.opsForValue().set(url, 0L, 1, TimeUnit.SECONDS);
		}
		Long num = redistemplate.opsForValue().increment(url,1);
		if (num> 1000) {
			redistemplate.opsForValue().set(url, 0L, 1, TimeUnit.SECONDS);
			System.out.println("每秒请求大于1000");
			ctx.setSendZuulResponse(false);
			//在 RequestContext 中设置一个值来标识是否成功，当为 true 的时候，后续的过滤器才执行，若为 false 则不执行。
            ctx.set("isSuccess", false);
            ctx.setResponseBody("{error:当前负载太高，请稍后重试}");
            ctx.getResponse().setContentType("application/json;charset=utf-8");
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
