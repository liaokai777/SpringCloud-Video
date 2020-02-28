package com.login.controller;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.pojo.Message;
import com.login.util.MapUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

@RestController // 相当于@ResponseBody ＋ @Controller合在一起的作用
public class SendMsgController {
	private static Logger logger = LoggerFactory.getLogger(SendMsgController.class);

	@RequestMapping("/sendmsg")
	public String sendMsg(@RequestBody Message msg) {
		Channel channel = MapUtil.getchannlMap().get(msg.getFrom());
		SocketAddress remoteAddress = channel.remoteAddress();// 远程地址
		logger.info("客户发送的消息" + msg.toString());
		channel.writeAndFlush(msg);
		return null;
	}
}
