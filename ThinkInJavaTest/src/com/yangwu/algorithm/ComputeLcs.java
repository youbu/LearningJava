package com.yangwu.algorithm;

public class ComputeLcs {
	public static int getLcsLength(String str1, String str2) {
		int lcs = 0;
		char[] char1;
		char[] char2;
		if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
			return 0;
		}
		char1 = str1.toCharArray();
		char2 = str2.toCharArray();
		int[][] char3 = new int[char1.length + 1][char2.length + 1];

		for (int m = 0; m < char1.length; m++) {
			for (int n = 0; n < char2.length; n++) {
				if (char1[m] == char2[n]) {
					char3[m + 1][n + 1] = char3[m][n] + 1;
				} else {
					char3[m + 1][n + 1] = Math.max(char3[m + 1][n], char3[m][n + 1]);
				}
			}
		}

		return char3[char1.length][char2.length];
	}

	public static void main(String[] args) {
		System.out.println(getLcsLength("gtaccgtca", "catcga"));
	}
}
