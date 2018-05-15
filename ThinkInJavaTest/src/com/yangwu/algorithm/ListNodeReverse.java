package com.yangwu.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListNodeReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(listNode.val);
        while(listNode.next != null){
        	listNode = listNode.next;
        	list.add(listNode.val);            
        }
        
        ArrayList<Integer>intergers = new ArrayList<>(list.size());
        while (list.size()>0) {
			intergers.add(list.pollLast());
		}
        return intergers;
	}
}

 class ListNode{
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
}
