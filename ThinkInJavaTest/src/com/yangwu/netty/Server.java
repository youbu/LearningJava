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
		//����������˽��ܿͻ�������
		EventLoopGroup boss = new NioEventLoopGroup();
		//��������ͨ��
		EventLoopGroup work = new NioEventLoopGroup();
		//�����������ߣ����ڷ������˵�һϵ������
		ServerBootstrap server = new ServerBootstrap();
		//�������߳���
		server.group(boss,work)
		//�ƶ�NIOģʽ
		.channel(NioServerSocketChannel.class)
		//����TCP������
		.option(ChannelOption.SO_BACKLOG,1024)
		//���÷��ͻ����С
		.option(ChannelOption.SO_SNDBUF, 32*1024)
		//���ý��ܻ����С
		.option(ChannelOption.SO_RCVBUF, 32*1024)
		//��������
		.option(ChannelOption.SO_KEEPALIVE, true)
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				//���þ������ݽ��ܷ����Ĵ���
				ch.pipeline().addLast(new ServerHander());
			}
		});
		
		//�����˿�
		ChannelFuture cf = server.bind(8888).sync();
		
		cf.channel().closeFuture().sync();
		System.out.println("���ӹر�");
		boss.shutdownGracefully();
		work.shutdownGracefully();
	}
}
