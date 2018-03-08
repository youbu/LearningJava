package com.yangwu.algorithm;

//字母全排列
public class AllSort {

	public static void permutation(char[] buf, int start, int end) {
		
		for (int i = 0; i <= end; i++) {
			System.out.print(buf[i] + ",");
		}
		System.out.println();
		
		if (start == end) {// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
			for (int i = 0; i <= end; i++) {
				System.out.print(buf[i]);
			}
			System.out.println();
		} else {// 多个字母全排列
			for (int i = start; i <= end; i++) {
				char temp = buf[start];// 交换数组第一个元素与后续的元素
				buf[start] = buf[i];
				buf[i] = temp;

				permutation(buf, start + 1, end);// 后续元素递归全排列

				temp = buf[start];// 将交换后的数组还原
				buf[start] = buf[i];
				buf[i] = temp;
			}
		}
	}

	public static void main(String[] args) {
		char[] buf = new char[] { 'a', 'b', 'c','d' };
		permutation(buf, 0, buf.length-1);
	}
}