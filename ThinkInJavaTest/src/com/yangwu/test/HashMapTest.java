package com.yangwu.test;

import java.util.HashMap;
import java.util.Map;

import com.yangwu.entity.Node;

public class HashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node node = new Node("A");
		Map<Node,String>map = new HashMap<Node,String>();
		map.put(node, "A");
		System.out.println(map.get(node));
		System.out.println(node.hashCode());
		
	}

}
