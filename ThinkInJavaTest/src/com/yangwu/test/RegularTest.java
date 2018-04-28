package com.yangwu.test;

import com.yangwu.util.Regular;

public class RegularTest {

	public static void main(String[] args) {
		
		String  regex = "^[1-9]\\d{4,14}";
		
		System.out.println(Regular.match("549047879", regex));
		
		System.out.println(Regular.match("0549047879", regex));
		
		System.out.println(Regular.match("47879", regex));
		
		System.out.println(Regular.match("549047a879", regex));
		
		System.out.println(Regular.match("549047879a", regex));
	}	

}
