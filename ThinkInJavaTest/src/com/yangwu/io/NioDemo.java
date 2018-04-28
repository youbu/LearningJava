package com.yangwu.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioDemo {

	private Selector selector;

	public void initServer(int port) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		this.selector = Selector.open();

		serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

		System.out.println("服务器端启动，端口：" + port);

	}

	public void listenSelector() throws IOException {
		while (true) {
			if (this.selector.select() == 0) {
				continue;
			}
			Iterator<?> iteratorKey = this.selector.selectedKeys().iterator();
			while (iteratorKey.hasNext()) {
				SelectionKey key = (SelectionKey) iteratorKey.next();
				iteratorKey.remove();
				handler(key);

			}
		}
	}

	private void handler(SelectionKey key) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		if (key.isAcceptable()) {
			System.out.println("新的客户端连接");
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
			SocketChannel channel = serverSocketChannel.accept();
			channel.configureBlocking(false);
			channel.register(this.selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) {
			SocketChannel channel = (SocketChannel) key.channel();

			
			int readData = channel.read(buffer);
			if (readData > 0) {
				buffer.flip();
				 String msg = new String(buffer.array(), 0,readData);
				 channel.register(selector, SelectionKey.OP_WRITE);
				buffer.clear(); // make buffer ready for writing
				System.out.println("收到消息：" + msg.toString());
			} 
		}else if(key.isWritable()){
			SocketChannel channel2 = (SocketChannel) key.channel();
			channel2.write(buffer.wrap("Hello World !".getBytes()));
			channel2.close();
		}
	}

	public static void main(String[] args) throws Exception {
		NioDemo demo = new NioDemo();
		demo.initServer(8888);
		demo.listenSelector();
	}

}
