package com.yangwu.sample;

public class FinallyTest {

	public static void main(String[] args) {
		System.out.println(FinallyTest.tes2());
		
	}

	public static String test1() {
		String res = "1";
		try {
			res = "2";
			return res;
		} finally {
			res = "3";
		}
	}
	
	public static int tes2() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80; 
        }
        catch (Exception e) {

            System.out.println("catch block");
        }
        finally {
            
            System.out.println("finally block");
            
            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }
        }
        
        return b;
    }
}
