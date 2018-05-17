package com.yangwu.algorithm;

import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。 假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序， 序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 * 
 * @author Administrator
 *
 */
public class PopPush {
	public boolean IsPopOrder(int[] pushA, int[] popA) {

		Stack<Integer> stack = new Stack<>();
		int index = 0;
		/**
		 * 1 压栈
		 * 2 判断栈顶是否等于popA的index下标对应的值
		 * 3 相等则出栈 index++
		 * 4 重复1~3循环 pushA.length次
		 * 5 栈为空则表示popA是弹出序列
		 */
		for (int i = 0; i < popA.length; i++) {
			stack.push(pushA[i]);
			while (!stack.isEmpty() && stack.peek() == popA[index]) {
				stack.pop();
				index++;
			}
		}
		return stack.isEmpty();
	}
}
