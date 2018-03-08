package com.yangwu.algorithm;

import java.util.Arrays;

public class MergeSort {
	/**
	 * 
	 * @param obj
	 * @param start
	 * @param end
	 *            obj.length()
	 */
	public static void mergeSort(int[] obj, int start, int end) {
		if (start >= end)
			return;

		int mid = (start + end) / 2;
		mergeSort(obj, start, mid);
		mergeSort(obj, mid + 1, end);

		merge(obj, start, mid, end);
	}

	private static void merge(int[] obj, int start, int mid, int end) {
		int[] obj1 = Arrays.copyOfRange(obj, start, mid + 1);
		int[] obj2 = Arrays.copyOfRange(obj, mid + 1, end);
		int m = 0, n = 0;
		int temp1, temp2;
		for (int i = start; i < end; i++) {
			temp1 = Integer.MAX_VALUE;
			temp2 = Integer.MAX_VALUE;
			if (m < obj1.length) {
				temp1 = obj1[m];
			}
			if (n < obj2.length) {
				temp2 = obj2[n];
			}

			if (temp1 < temp2) {
				m++;
				obj[i] = temp1;
			} else if (temp2 < temp1) {
				obj[i] = temp2;
				n++;
			}
		}

	}
}
