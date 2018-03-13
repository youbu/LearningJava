package com.yangwu.test;

import com.yangwu.entity.LinkList;
import com.yangwu.entity.LinkNode;

public class LinkListTest {

	public static void main(String[] args) {
		LinkList<String>list = new LinkList<String>(new LinkNode<String>("A"));
		
		list.add(new LinkNode<String>("B"));
		
		list.add(new LinkNode<String>("C"));
		
		list.add(new LinkNode<String>("D"));
		
		list.add(new LinkNode<String>("E"));
		
		System.out.println(list.toString());
		
		list.reserve();
		
		System.out.println(list.toString());
	}

}
