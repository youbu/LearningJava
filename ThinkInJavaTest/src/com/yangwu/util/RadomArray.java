package com.yangwu.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RadomArray {

	public static void main(String[] args) {
		int[] ints = RadomArray.getRadomArrays(5000010, 0, 5000010);
		System.out.println(ints.length);
		try (BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File("aa.txt")))) {
			for (int i : ints) {

				System.out.println(i);

				bs.write((i + "\r\n").getBytes());

			}
			bs.flush();
			bs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int[] getRadomArrays(int length, int min, int max) {
		int[] res;
		Random radom = new Random(10);
		// res = radom.ints().limit(length).map(new IntUnaryOperator() {
		//
		// @Override
		// public int applyAsInt(int num) {
		//
		// return num < 0 ? num * -1 : num;
		// }
		// }).toArray();

		res = radom.ints(min, max).limit(length).map((num) -> num < 0 ? num * -1 : num).toArray();
		return res;
	}

	public static List<Integer> getRandomList(int lenght, int min, int max) {
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random(10);
		random.ints(min, max).limit(lenght).map((num) -> num < 0 ? num * -1 : num).forEach((num) -> list.add(num));
		return list;
	}
}
