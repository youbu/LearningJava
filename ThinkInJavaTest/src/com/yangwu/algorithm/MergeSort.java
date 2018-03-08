package com.yangwu.algorithm;

import java.util.Arrays;

public class MergeSort {
	public static void mergeSort(Object[] obj, int start, int end) {
		if (start >= end)
			return;

		int mid = (start + end) / 2;
		mergeSort(obj, start, mid);
		mergeSort(obj, mid + 1, end);
		
		merge(obj,start,mid,end);
	}

	private static void merge(Object[] obj, int start, int mid, int end) {
		Object[] obj1 = Arrays.copyOfRange(obj, start, mid);
		Object[] obj2 = Arrays.copyOfRange(obj, mid +1 , end);
		int index = start;
	}
}
