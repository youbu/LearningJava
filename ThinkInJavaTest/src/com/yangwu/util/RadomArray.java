package com.yangwu.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class RadomArray {

	public static void main(String[] args) {
		int[] ints = RadomArray.getRadomArrays(5000010);
		System.out.println(ints.length);
		try(BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File("aa.txt")))){
			for (int i : ints) {
				
				System.out.println(i);
				
				bs.write((i + "\r\n").getBytes());
				
			}
			bs.flush();
			bs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static int[] getRadomArrays(int length) {
		int[] res;
		Random radom = new Random(10);
//		res = radom.ints().limit(length).map(new IntUnaryOperator() {
//
//			@Override
//			public int applyAsInt(int num) {
//
//				return num < 0 ? num * -1 : num;
//			}
//		}).toArray();

		res = radom.ints().limit(length).map((num)-> num < 0 ? num * -1 : num).toArray();
		return res;
	}
}
