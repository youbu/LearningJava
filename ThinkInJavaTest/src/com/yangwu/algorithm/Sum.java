package com.yangwu.algorithm;
/**
 * ��1+2+3+...+n��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
 * ˼·��ʹ�ö�·���������Ϊ�ݹ����������
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
