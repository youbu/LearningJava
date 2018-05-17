package com.yangwu.algorithm;

import java.util.Stack;

/**
 * ���������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������Ƿ�Ϊ��ջ�ĵ���˳�� ����ѹ��ջ���������־�����ȡ�
 * ��������1,2,3,4,5��ĳջ��ѹ��˳�� ����4��5,3,2,1�Ǹ�ѹջ���ж�Ӧ��һ���������У�
 * ��4,3,5,1,2�Ͳ������Ǹ�ѹջ���еĵ������С���ע�⣺���������еĳ�������ȵģ�
 * 
 * @author Administrator
 *
 */
public class PopPush {
	public boolean IsPopOrder(int[] pushA, int[] popA) {

		Stack<Integer> stack = new Stack<>();
		int index = 0;
		/**
		 * 1 ѹջ
		 * 2 �ж�ջ���Ƿ����popA��index�±��Ӧ��ֵ
		 * 3 ������ջ index++
		 * 4 �ظ�1~3ѭ�� pushA.length��
		 * 5 ջΪ�����ʾpopA�ǵ�������
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
