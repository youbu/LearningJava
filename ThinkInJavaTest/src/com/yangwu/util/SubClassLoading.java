package com.yangwu.util;

public class SubClassLoading extends ClassLoading {


	public SubClassLoading() {
		System.out.println("子类的构造方法");
	}
	
	{System.out.println("子类的成员变量");}
	
	static {System.out.println("子类的静态成员变量");}
	
	public static void main(String[] args) {
		System.out.println("加载开始");
		
		new SubClassLoading();
		
		System.out.println("加载结束");
	}

}
