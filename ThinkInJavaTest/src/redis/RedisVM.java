package redis;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisVM {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(6382);
		Socket socket = serverSocket.accept();
		
		InputStream reader =  socket.getInputStream();
		OutputStream writer = socket.getOutputStream();
		byte[] b = new byte[1024];
		
		reader.read(b);
		
		System.out.println(new String(b));
		writer.write("OK\r\n".getBytes());
	}
}
