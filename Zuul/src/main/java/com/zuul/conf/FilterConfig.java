package com.zuul.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zuul.filter.FlowFilter;
import com.zuul.filter.IpFilter;

@Configuration
public class FilterConfig {
	@Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }
	
	@Bean
    public FlowFilter flowFilter() {
        return new FlowFilter();
    }
}
