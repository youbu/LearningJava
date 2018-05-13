package com.yangwu.designpattern.template;

public abstract class DoSomething {
	public void executor() {
		step1();
		step2();
		step3();
	}
	
	public void step1() {
		System.out.println("start the operate !");
	}
	
	public abstract void step2();
	
	public void step3() {
		System.out.println("finish the operate !");
	}
}
