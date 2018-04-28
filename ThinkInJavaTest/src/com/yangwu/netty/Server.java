package com.yangwu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		//处理服务器端接受客户端连接
		EventLoopGroup boss = new NioEventLoopGroup();
		//进行网络通信
		EventLoopGroup work = new NioEventLoopGroup();
		//创建辅助工具，用于服务器端的一系列配置
		ServerBootstrap server = new ServerBootstrap();
		//绑定两个线程组
		server.group(boss,work)
		//制定NIO模式
		.channel(NioServerSocketChannel.class)
		//设置TCP缓冲区
		.option(ChannelOption.SO_BACKLOG,1024)
		//设置发送缓冲大小
		.option(ChannelOption.SO_SNDBUF, 32*1024)
		//设置接受缓冲大小
		.option(ChannelOption.SO_RCVBUF, 32*1024)
		//保持连接
		.option(ChannelOption.SO_KEEPALIVE, true)
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				//配置具体数据接受方法的处理
				ch.pipeline().addLast(new ServerHander());
			}
		});
		
		//綁定端口
		ChannelFuture cf = server.bind(8888).sync();
		
		cf.channel().closeFuture().sync();
		System.out.println("连接关闭");
		boss.shutdownGracefully();
		work.shutdownGracefully();
	}
}
