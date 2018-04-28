package com.yangwu.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientDemo {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8888);
		System.out.println("客户端启动成功");
		BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
		
		os.write("Send Data to Server！".getBytes("UTF-8"));
		os.flush();
//		os.close();
		
		BufferedInputStream is = new BufferedInputStream(socket.getInputStream());
		byte[] bs = new byte[1024];
		while (is.read(bs)>0) {
			System.out.println(new String(bs));
		}
		System.in.read();
		
	}
}
