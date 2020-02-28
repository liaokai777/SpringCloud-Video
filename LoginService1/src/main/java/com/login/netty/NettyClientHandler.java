package com.login.netty;

import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.login.pojo.Message;
import com.login.pojo.ResponseFileInfo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		System.out.println("loginNettyClient handlerAdded");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		System.out.println("loginNettyClient handlerRemoved");
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) { // (1)
		System.out.println("loginNettyClient channelActive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("loginNettyClient channelRead");
		Channel channel = ctx.channel();
		SocketAddress remoteAddress = channel.remoteAddress();
		if(msg instanceof ResponseFileInfo) {
			logger.info("收到"+remoteAddress+"服务器发送的"+msg.toString());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
