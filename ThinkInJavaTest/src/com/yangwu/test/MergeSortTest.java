package com.yangwu.test;

import com.yangwu.algorithm.MergeSort;
import com.yangwu.util.RadomArray;

public class MergeSortTest {

	public static void main(String[] args) {
		int[] obj = RadomArray.getRadomArrays(20, 0, 100);
		for(int i : obj) {
			System.out.print(i + "\t");
		}
		System.out.println();
		MergeSort.mergeSort(obj, 0, obj.length-1);
		for(int i : obj) {
			System.out.print(i + "\t");
		}
	}

}
