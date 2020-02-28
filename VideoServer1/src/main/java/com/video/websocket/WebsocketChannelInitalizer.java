package com.video.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketChannelInitalizer extends ChannelInitializer<Channel>{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//websocket协议本身是基于http协议的，所以这边也要使用http解编码器
		pipeline.addLast(new HttpServerCodec());
		/*出入站信息都是通过他发送到headcontext，这样我们所有的消息都是被当成一个个队列元素塞入队列，然后每次取出一个消息发送*/
		pipeline.addLast(new ChunkedWriteHandler());
		/*当我们用POST方式请求服务器的时候，对应的参数信息是保存在message body中的,
		如果只是单纯的用HttpServerCodec是无法完全的解析Http POST请求的，
		因为HttpServerCodec只能获取uri中参数，所以需要加上HttpObjectAggregator*/
		//对大数据流的支持
		pipeline.addLast(new HttpObjectAggregator(1024 * 64));
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true, 65536 * 10));
		pipeline.addLast(new WebSocketHandler());
	}

}
