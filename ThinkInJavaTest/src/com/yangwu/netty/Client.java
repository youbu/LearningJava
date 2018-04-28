package com.yangwu.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		
		Bootstrap bootstrap = new Bootstrap();
		
		bootstrap.group(group)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ClientHandler());
			}
		});
		
		ChannelFuture cf = bootstrap.connect("localhost", 8888).sync();
		
		cf.channel().writeAndFlush(Unpooled.copiedBuffer("发送给服务器端消息", CharsetUtil.UTF_8));
		
		cf.channel().closeFuture().sync();
		group.shutdownGracefully();
	}
}
