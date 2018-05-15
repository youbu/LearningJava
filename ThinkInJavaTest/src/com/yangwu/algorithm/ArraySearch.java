package com.yangwu.algorithm;

/**
 * ��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������ ÿһ�ж����մ��ϵ��µ�����˳������
 * �����һ������������������һ����ά�����һ���������ж��������Ƿ��и�������
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
