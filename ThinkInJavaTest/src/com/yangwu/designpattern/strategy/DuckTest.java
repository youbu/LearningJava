package com.yangwu.designpattern.strategy;

import com.yangwu.designpattern.strategy.duck.MallardDuck;
import com.yangwu.designpattern.strategy.duck.MuteDuck;
import com.yangwu.designpattern.strategy.duck.RedHeadDuck;

public class DuckTest {

	public static void main(String[] args) {
		System.out.println("����ģʽ�������㷨�壬�ֱ��װ������������֮������໥�滻�����㷨�ı仯������ʹ���㷨�Ŀͻ���"
				+ "\r\nһ�����ڷ�װ������Ϊ����̬��ע�뵽��ͬ�Ķ����У��Ӷ�������ͬ�ı���");
		System.out.println("*************************************");
		MallardDuck mallardDuck = new MallardDuck();
		mallardDuck.display();
		mallardDuck.swim();
		mallardDuck.performFly();
		mallardDuck.performQuack();
		System.out.println("*************************************");
		MuteDuck muteDuck = new MuteDuck();
		muteDuck.display();
		muteDuck.swim();
		muteDuck.performFly();
		muteDuck.performQuack();
		System.out.println("*************************************");
		RedHeadDuck redHeadDuck = new RedHeadDuck();
		redHeadDuck.display();
		redHeadDuck.swim();
		redHeadDuck.performFly();
		redHeadDuck.performQuack();
	}

}
