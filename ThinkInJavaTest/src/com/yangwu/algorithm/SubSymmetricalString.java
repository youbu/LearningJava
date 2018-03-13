package com.yangwu.algorithm;

/**
 * 最大对称子字符串
 * 
 * @author cheneychen
 *
 */
public class SubSymmetricalString {

	public static String getSubSymmetricalString(String str) {

		char[] chars = str.toCharArray();
		String res = "";

		int index = 1;
		int left, right;
		int start = 0, end = 0;
		while (index > 0 && index < chars.length - 1) {
			// 当对称子字符串为偶数
			left = index - 1;
			right = index + 1;
			while (left >= 0 && right <= chars.length - 1 && chars[left] == chars[right]) {
				left--;
				right++;
			}
			if (right - left - 1 > end - start) {
				start = left + 1;
				end = right;
			}

			// 奇数的时候
			left = index;
			right = index + 1;
			while (left >= 0 && right <= chars.length - 1 && chars[left] == chars[right]) {
				left--;
				right++;
			}
			if (right - left - 1 > end - start) {
				start = left + 1;
				end = right;
			}

			index++;
		}
		res = str.substring(start, end);
		return res;
	}

	public static void main(String[] args) {
		String str = SubSymmetricalString.getSubSymmetricalString("go00ogle12345654321");
		System.out.println(str);

	}

}
