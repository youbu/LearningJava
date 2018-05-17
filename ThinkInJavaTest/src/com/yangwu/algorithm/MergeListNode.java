package com.yangwu.algorithm;

public class MergeListNode {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1);
		ListNode list3 = new ListNode(3);
		ListNode list5 = new ListNode(5);
		ListNode list7 = new ListNode(7);
		ListNode list9 = new ListNode(9);
		
		list1.next = list3;
		list3.next = list5;
		list5.next = list7;
		list7.next = list9;
		
		
		
		ListNode list2 = new ListNode(2);
		ListNode list4 = new ListNode(4);
		ListNode list6 = new ListNode(6);
		ListNode list8 = new ListNode(8);
		ListNode list10 = new ListNode(10);
		ListNode list12 = new ListNode(12);
		
		list2.next=list4;
		list4.next=list6;
		list6.next=list8;
		list8.next =list10;
		list10.next=list12;
		
		MergeListNode merge = new MergeListNode();
		
		
		ListNode node = merge.Merge(list1, list2);
		merge.print(node);
	}

	public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode node = null;
        
        if (list1 == null) {
			return list2;
		}
        
        if (list2 == null) {
			return list1;
		}
        
        if (list1.val < list2.val) {
			node = list1;
			node.next = Merge(list1.next, list2);
		}else {
			node = list2;
			node.next = Merge(list1, list2.next);
		}
        
        return node;
    }
    
    
    public void print(ListNode node) {
    	while (node!= null) {
			System.out.println(node.val);
			node = node.next;		
		}
    }
}

