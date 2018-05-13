package com.yangwu.designpattern.template;

public class TemplateTest {

	public static void main(String[] args) {
		System.out.println("模板方法模式\r\n" + 
				"	在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类在不改变算法结构的情况下，重新定义算法中的某些步骤。");
		System.out.println("************************");
		DoSomething makecoffee = new MakeCoffee();
		makecoffee.executor();
		
		System.out.println("************************");
		DoSomething makeTea = new MakeTea();
		makeTea.executor();
	}

}
