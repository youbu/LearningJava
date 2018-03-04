package com.yangwu.test;

import java.util.List;

import com.yangwu.sort.InsertSort;
import com.yangwu.util.RadomArray;

public class InsertSortTest {

	public static void main(String[] args) {
		List<Integer> list = RadomArray.getRandomList(10,0,100);
		
		InsertSort.insertSort(list, (o1, o2) -> o1 - o2);
		list.stream().forEach(System.out::println);
	}


}
