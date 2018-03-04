package com.yangwu.sort;

import java.util.Comparator;
import java.util.List;

public class QuickSort {

	
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
