package com.yangwu.netty;

import java.net.SocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;

		try {
			byte[] resp = new byte[buf.readableBytes()];
			buf.readBytes(resp);

			String str = new String(resp, "UTF-8");

			System.out.println("�յ�����������Ӧ �� " + str);
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		System.out.println("bind");
		super.bind(ctx, localAddress, promise);	
		
	}
	
	
}
