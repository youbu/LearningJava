package com.yangwu.sort;

public class CountSort {
	public static void countSort(int[] nums) {
		print("nums ", nums);
		int max = nums[0], min = nums[0];
		for (int i : nums) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}

		int length = max - min + 1;
		int[] count = new int[length];
		for (int i = 0; i < nums.length; i++) {
			count[nums[i] - min] += 1;
		}
		System.out.println();
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[i]; j++) {
				System.out.print(i + min + " ");
			}
		}

//		System.out.println("min : " + min + " max : " + max);
		
//		print("count ", count);
	}

	private static void print(String string, int[] count) {
		System.out.println();
		System.out.print(string + " : ");
		for (int t : count) {
			System.out.print(t + " ");
		}
	}

}
