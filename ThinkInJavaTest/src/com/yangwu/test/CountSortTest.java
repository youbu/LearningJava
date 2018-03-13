package com.yangwu.test;

import com.yangwu.sort.CountSort;
import com.yangwu.util.RadomArray;

public class CountSortTest {

	public static void main(String[] args) {
		 int[] ints = RadomArray.getRadomArrays(10, 0, 2000);
//		int[] ints = { 1, 2, 4, 6, 3, 3, 1 };
		CountSort.countSort(ints);
	}

}
