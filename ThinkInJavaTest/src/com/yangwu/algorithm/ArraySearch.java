package com.yangwu.algorithm;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序， 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * @author Administrator
 *
 */
public class ArraySearch {

	public static void main(String[] args) {
		int[][] array = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int target = 6;
		System.out.println( find(target, array));
		
		String string = "We Are Happy.";
		System.out.println(string.replaceAll("\\s", "%20"));
	}

	public static boolean find(int target, int[][] array) {
		int i = array.length - 1;
		int colNum = array[0].length;
		int j = 0;
		while (i > 0 && j < colNum) {
			if (array[i][j]>target) {
				i--;
			}else if (array[i][j]<target) {
				j++;
			}else {
				System.out.println("array["+ i +"]"+"["+j+"] = " + target);
				return true;
			}
			
		}
		return false;
	}
}
