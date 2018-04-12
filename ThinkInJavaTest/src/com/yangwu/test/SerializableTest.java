package com.yangwu.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.yangwu.entity.Node;

public class SerializableTest {

	public static void main(String[] args) {
//		Serialize();
		deSerialize();
	}

	public static void Serialize() {
		Node node = new Node("node");
		node.setInDegree(10);		
		
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("Node")));
			oo.writeObject(node);
			oo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void deSerialize() {
		try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("Node")));
			Node node = (Node)oi.readObject();
			System.out.println(node.getNodeName() + " : " + node.getInDegree());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
