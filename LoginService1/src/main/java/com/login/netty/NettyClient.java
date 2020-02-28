package com.login.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyClient {
	private static Logger logger = LoggerFactory.getLogger(NettyClient.class);
	private static NioEventLoopGroup workerGroup;
	private static Bootstrap b;
	private NettyClient() {
		
	}
	static {
		//startClient();
		//logger.info("NettyClient启动完成");
	}
	public static Bootstrap getBootstrap() {
		return b;
	}
	public static void startClient() {
		logger.info("NettyClient启动完成 可以发送文件");
		//工作线程组
		workerGroup = new NioEventLoopGroup();
		try {
			b = new Bootstrap(); // (1)引导
			b.group(workerGroup); // (2)
			b.channel(NioSocketChannel.class); // (3)
			b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE,ClassResolvers.cacheDisabled(null)));// 解码
					ch.pipeline().addLast("encoder", new ObjectEncoder());// 编码
					
					ch.pipeline().addLast(new NettyClientHandler());//适配器
				}
			});
			// Start the client.
			//ChannelFuture f = b.connect(new InetSocketAddress(ip,port)); // (5)
			//channel = f.channel();
		} finally {
			//workerGroup.shutdownGracefully();
		}
	}
	public static void shutdownGracefully() {
		workerGroup.shutdownGracefully();
	}
}
