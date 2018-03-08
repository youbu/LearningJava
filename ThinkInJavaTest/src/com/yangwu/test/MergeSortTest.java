package com.yangwu.test;

import com.yangwu.algorithm.MergeSort;
import com.yangwu.util.RadomArray;

public class MergeSortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] obj = RadomArray.getRadomArrays(10, 0, 100);
		for(int i : obj) {
			System.out.print(i + " ");
		}
		System.out.println();
		MergeSort.mergeSort(obj, 0, obj.length);
		for(int i : obj) {
			System.out.print(i + " ");
		}
	}

}
