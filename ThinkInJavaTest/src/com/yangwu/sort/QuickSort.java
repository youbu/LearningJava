package com.yangwu.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.yangwu.util.RadomArray;

public class QuickSort {

	public static void main(String[] args) {
		int[] nums = RadomArray.getRadomArrays(10);
		List<Integer> list = new ArrayList<Integer>();
		for (int num : nums) {
			list.add(num);
		}

		quickSort(list, 0, list.size() - 1, (o1, o2) -> o1 - o2);
		list.stream().forEach(System.out::println);
	}

	public static <T> void quickSort(List<T> list, int left, int right, Comparator<? super T> c) {
		if(left > right) {
			return;
		}
		T temp = list.get(left);
		T t;
		
		int i = left;
		int j = right;

		while (i != j) {
			while (c.compare(list.get(j), temp) >= 0 && i < j)
				j--;

			while (c.compare(list.get(i), temp) <= 0 && i < j)
				i++;

			if (i < j) {
				t = list.get(i);
				list.set(i, list.get(j));
				list.set(j, t);
			}
		}

		list.set(left, list.get(i));
		list.set(i, temp);

		quickSort(list, left, i - 1, c);
		quickSort(list, i + 1, right, c);

	}
}
