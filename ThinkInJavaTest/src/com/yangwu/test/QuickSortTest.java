package com.yangwu.test;

import java.util.List;

import com.yangwu.sort.QuickSort;
import com.yangwu.util.RadomArray;

public class QuickSortTest {

	public static void main(String[] args) {
		List<Integer> list = RadomArray.getRandomList(10, 0, 100);
		list.stream().forEach(o -> System.out.print(o + ","));
		System.out.println();
		QuickSort.quickSort(list, 0, list.size() - 1, (o1, o2) -> o1 - o2);
		list.stream().forEach(o -> System.out.print(o + ","));
	}

}
