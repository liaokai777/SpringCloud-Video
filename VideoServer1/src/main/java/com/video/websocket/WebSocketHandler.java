package com.video.websocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.video.service.VideoService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
/**
 * TextWebSocketFrame: 在netty中，用于为websocket专门处理文本的对象，frame是消息的载体
 * @author AS US
 *
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	 //保留所有与服务器建立连接的channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		System.out.println("websocketserver handlerAdded"+ctx.channel());
		channelGroup.add(ctx.channel());
	}
	//当触发handlerRemoved,ChannelGroup会自动移除对应的客户端的channel
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		System.out.println("websocketserver handlerRemoved"+ctx.channel());
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) { // (1)
		System.out.println("websocketserver channelActive"+ctx.channel());
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
		System.out.println("websocketserver channelRead0"+ctx.channel());
		Channel channel = ctx.channel();
		String channelId = channel.id().toString();
		// 1. 获取客户端发送的消息
		String content = frame.text().trim();
		System.out.println("content:"+content);
		if("startZB".equals(content)) {
			System.out.println("准备开启摄像头");
			VideoService.doService(channelId, true);
			channel.writeAndFlush(new TextWebSocketFrame("OK"));
		}else if("endZB".equals(content)){
			System.out.println("准备关闭摄像头");
			VideoService.doService(channelId, false);
		}
		//管道组
		/*channelGroup.forEach(ch->{
			if(ch!=channel) {
				logger.info("服务器给其他在线客户发送消息");
				ch.writeAndFlush(new TextWebSocketFrame(content));
			}else {
				logger.info("服务器响应");
				ch.writeAndFlush(new TextWebSocketFrame("success"));
			}
		});*/
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("websocketserver exceptionCaught"+ctx.channel());
		cause.printStackTrace();
		ctx.close();
	}

}
