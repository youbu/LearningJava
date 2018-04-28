package com.yangwu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class ServerHander extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf =(ByteBuf) msg;
		
		byte[] req = new byte[buf.readableBytes()];
		
		buf.readBytes(req);
		
		String body = new String(req, "UTF-8");
		System.out.println("Server : " + body);
		
		String response = "已收到来自客户端的消息" ;
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(response, CharsetUtil.UTF_8))
		.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}
