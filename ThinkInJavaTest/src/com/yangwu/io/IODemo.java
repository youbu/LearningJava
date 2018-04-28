package com.yangwu.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统IO
 * @author Administrator
 *
 */
public class IODemo {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("IO Server 启动，端口：8080");
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("有新的连接");

			//引入多线程
			new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
					InputStream inputStream = socket.getInputStream();
					byte[] b = new byte[1024];
					while (true) {
						int data = inputStream.read(b);
						if (data != -1) {
							String info = new String(b, 0, data);
							System.out.println(Thread.currentThread().getName()+" : " +info);
						} else {
							System.out.println(Thread.currentThread().getName()+" : " +"关闭连接。");
							break;
						}
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			
			
			
			
		}
	}

}
