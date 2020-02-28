package com.login.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.login.LoginServiceApplication;
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {LoginServiceApplication.class})
public class Producer {
	@Autowired
	private RocketMQTemplate rocketmqtemplate;
	
	@Test
	public void  sendMsg() {
		rocketmqtemplate.convertAndSend("springboot-rocketmq", "hello springbootmq");
	}
}
