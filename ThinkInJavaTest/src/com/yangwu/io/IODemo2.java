package com.yangwu.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * �̳߳�IO
 * 
 * @author Administrator
 *
 */
public class IODemo2 {

	public static void main(String[] args) throws Exception {

		ExecutorService pool = Executors.newFixedThreadPool(4);

		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("IO Server �������˿ڣ�8080");
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("���µ�����");

			// ������߳�
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						InputStream inputStream = socket.getInputStream();
						byte[] b = new byte[1024];
						while (true) {
							int data = inputStream.read(b);
							if (data != -1) {
								String info = new String(b, 0, data);
								System.out.println(Thread.currentThread().getName() + " : " + info);
							} else {
								System.out.println(Thread.currentThread().getName() + " : " + "�ر����ӡ�");
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}
	}

}
