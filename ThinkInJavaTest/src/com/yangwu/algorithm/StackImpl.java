package com.yangwu.algorithm;

/**
 * 模拟堆栈，并能返回栈中的最小元素
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
