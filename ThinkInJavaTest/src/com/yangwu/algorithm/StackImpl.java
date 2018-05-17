package com.yangwu.algorithm;

/**
 * ģ���ջ�����ܷ���ջ�е���СԪ��
 */
import java.util.Iterator;
import java.util.Stack;

public class StackImpl {

	Stack<Integer> stack = new Stack<>();

	public void push(int node) {
		stack.push(node);
	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int min() {
		Iterator<Integer> it = stack.iterator();
		int min = stack.peek();
		int temp = 0;
		while (it.hasNext()) {
			temp = it.next();
			if (min > temp) {
				min = temp;
			}
		}
		return min;
	}
}
