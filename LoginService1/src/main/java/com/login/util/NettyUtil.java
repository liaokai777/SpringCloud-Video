package com.login.util;

import java.net.InetSocketAddress;

import com.login.netty.NettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class NettyUtil {
	public NettyUtil() {
	}
	/**
	 * 创建聊天管道
	 * @return
	 */
	public Channel createChannel(){
		Bootstrap b = NettyClient.getBootstrap();
		ChannelFuture f;
		Channel channel = null;
		try {
			f = b.connect(new InetSocketAddress("127.0.0.1",9999)).sync();
			channel = f.channel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channel;
	}
}
