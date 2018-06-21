package com.yangwu.algorithm;

public class ReOrderArray {

	public static void main(String[] args) {
		ReOrderArray reOrderArray = new ReOrderArray();
		int[] arr = { 1, 4, 2, 6, 23, 785, 234, 7568, 3 };
		reOrderArray.print(arr);
		reOrderArray.reOrderArray(arr);
		reOrderArray.print(arr);
	}

	public void reOrderArray(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i + 1; j > 0 && j < array.length; j--) {
				if (array[j] % 2 == 1 && array[j - 1] % 2 == 0) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public void print(int arr[]) {
		for (int i : arr) {
			System.out.print(i + ",");
		}
		System.out.println();
	}
}
