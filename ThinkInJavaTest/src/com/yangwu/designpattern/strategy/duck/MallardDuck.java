package com.yangwu.designpattern.strategy.duck;

import com.yangwu.designpattern.strategy.fly.FlyWithWings;
import com.yangwu.designpattern.strategy.quack.Quack;

public class MallardDuck extends Duck {

	public MallardDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("这是一只野鸭 ！");
	}

}
