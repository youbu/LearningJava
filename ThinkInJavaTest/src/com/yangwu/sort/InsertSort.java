package com.yangwu.sort;

import java.util.Comparator;
import java.util.List;

public class InsertSort {
	public static <T> void insertSort(List<T> list, Comparator<? super T> c) {
		T temp ;
		int j;
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			for (j = i; j >0 && c.compare(list.get(j-1), temp) > 0; j--) {
				list.set(j, list.get(j-1));		
			}
			list.set(j, temp);
		}
	}
}
