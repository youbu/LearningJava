package com.yangwu.netty;


import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

public class UDPClient {

	public void run(int port) throws InterruptedException {
		EventLoopGroup b = new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap  = new Bootstrap();
			
			bootstrap.group(b).channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new UDPClientHandler());
			
			Channel ch = bootstrap.bind(0).sync().channel();
			
			//广播消息
			ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语字典查询？",CharsetUtil.UTF_8),
					new InetSocketAddress("255.255.255.255",port)));
			
			if (!ch.closeFuture().await(15000)) {
				System.out.println("查询超时");
			}
		} finally {
			b.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		UDPClient client = new UDPClient();
		client.run(8888);

	}

}
