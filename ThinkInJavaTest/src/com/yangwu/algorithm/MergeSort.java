package com.yangwu.algorithm;

import java.util.Arrays;

public class MergeSort {
	/**
	 * 
	 * @param obj
	 * @param start
	 * @param end obj.length()-1            
	 */
	public static void mergeSort(int[] obj, int start, int end) {
		if (start >= end)
			return;

		int mid = (start + end) / 2;
		mergeSort(obj, start, mid);
		mergeSort(obj, mid + 1, end);

		merge(obj, start, mid, end);
		
//		for(int i : obj) {
//			System.out.print(i + "\t");
//		}
//		System.out.println();
	}

//	private static void merge(int[] a, int low, int mid, int high) {
//		int[] temp = new int[high-low+1];
//        int i= low;
//        int j = mid+1;
//        int k=0;
//        // 把较小的数先移到新数组中
//        while(i<=mid && j<=high){
//            if(a[i]<a[j]){
//                temp[k++] = a[i++];
//            }else{
//                temp[k++] = a[j++];
//            }
//        }
//        // 把左边剩余的数移入数组 
//        while(i<=mid){
//            temp[k++] = a[i++];
//        }
//        // 把右边边剩余的数移入数组
//        while(j<=high){
//            temp[k++] = a[j++];
//        }
//        // 把新数组中的数覆盖nums数组
//        for(int x=0;x<temp.length;x++){
//            a[x+low] = temp[x];
//        }		
//	}

	private static void merge(int[] obj, int start, int mid, int end) {
		int[] obj1 = Arrays.copyOfRange(obj, start, mid + 1);
		int[] obj2 = Arrays.copyOfRange(obj, mid + 1, end + 1);
//		print("obj " , obj);
//		System.out.println("Start : " + start + " mid : " + mid + " end : " + end);
//		print("obj1 " , obj1);
//		print("obj2 " , obj2);
		int m = 0, n = 0;
		int temp1, temp2;
		for (int i = start; i <= end; i++) {
			temp1 = Integer.MAX_VALUE;
			temp2 = Integer.MAX_VALUE;
			if (m < obj1.length) {
				temp1 = obj1[m];
			}
			if (n < obj2.length) {
				temp2 = obj2[n];
			}

			if (temp1 < temp2) {
				m++;
				obj[i] = temp1;
			} else if (temp2 < temp1) {
				obj[i] = temp2;
				n++;
			}else if(temp1 == temp2 && temp1 != Integer.MAX_VALUE) {
				m++;
				obj[i] = temp1;
			}
		}
//		System.out.println();
	}

private static void print(String string, int[] obj1) {
	
	System.out.print(string + ":");
	for(int i : obj1) {
		System.out.print(i + " ");
	}
	System.out.println();
}


}
