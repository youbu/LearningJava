package com.yangwu.algorithm;
/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 思路：使用短路与操作符作为递归的收敛条件
 * @author Administrator
 *
 */
public class Sum {
	public static void main(String[] args) {
		Sum sum = new Sum();
		System.out.println(sum.Sum_Solution2(100));
		System.out.println(((int)Math.pow(100, 2) + 100)>>1);

	}
	 public int Sum_Solution(int n) {
	        int res = n;
	        
	        boolean flg = (n>0) && (res+=Sum_Solution(n-1))!=0;
	        
	        return res;
	    }
	 
	 public int Sum_Solution2(int n) {
	        int sum = (int) (Math.pow(n,2) + n);
	        System.out.println(sum);
	       return sum>>1;
	    }
}
