package com.yangwu.designpattern.template;

public class TemplateTest {

	public static void main(String[] args) {
		System.out.println("ģ�巽��ģʽ\r\n" + 
				"	��һ�������ж���һ���㷨�ĹǼܣ�����һЩ�����ӳٵ������С�ģ�巽��ʹ�������ڲ��ı��㷨�ṹ������£����¶����㷨�е�ĳЩ���衣");
		System.out.println("************************");
		DoSomething makecoffee = new MakeCoffee();
		makecoffee.executor();
		
		System.out.println("************************");
		DoSomething makeTea = new MakeTea();
		makeTea.executor();
	}

}
