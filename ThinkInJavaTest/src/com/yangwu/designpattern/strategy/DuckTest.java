package com.yangwu.designpattern.strategy;

import com.yangwu.designpattern.strategy.duck.MallardDuck;
import com.yangwu.designpattern.strategy.duck.MuteDuck;
import com.yangwu.designpattern.strategy.duck.RedHeadDuck;

public class DuckTest {

	public static void main(String[] args) {
		System.out.println("策略模式：定义算法族，分别封装起来，让他们之间可以相互替换，让算法的变化独立于使用算法的客户。"
				+ "\r\n一般用于封装各种行为，动态的注入到不同的对象中，从而产生不同的表现");
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
