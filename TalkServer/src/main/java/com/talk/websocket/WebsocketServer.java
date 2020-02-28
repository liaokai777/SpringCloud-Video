package com.talk.websocket;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class WebsocketServer {
	private static Logger logger = LoggerFactory.getLogger(WebsocketServer.class);
	public static void startServer(){
		logger.info("启动聊天websocket服务，端口9998");
		//接收连接线程组
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		//工作线程组
		NioEventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		//指定使用的channel
		serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new WebsocketChannelInitalizer())
		.option(ChannelOption.SO_BACKLOG, 2048)//serverSocketchannel的设置，链接缓冲池的大小
		.childOption(ChannelOption.SO_KEEPALIVE, true)//socketchannel的设置,维持链接的活跃，清除死链接
		.childOption(ChannelOption.TCP_NODELAY, true);//socketchannel的设置,关闭延迟发送
		try {
			ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(9998)).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		
	}
	
}
