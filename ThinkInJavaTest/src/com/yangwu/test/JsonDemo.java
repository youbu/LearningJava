package com.yangwu.test;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.yangwu.entity.Node;

public class JsonDemo {

	public static void main(String[] args) {
		serializeByJson();
	}

	public static void serializeByJson() {
		Node node = new Node("node");
		node.setInDegree(10);		
		ObjectMapper ob = new ObjectMapper();
		try {
			byte[] bytes =	ob.writeValueAsBytes(node);
			
			Node node2 = ob.readValue(bytes, Node.class);
			node2.setNodeName("node2");
			System.out.println(node2.getNodeName() + " : " + node2.getInDegree() );
			System.out.println(node.getNodeName() + " : " + node.getInDegree() );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
