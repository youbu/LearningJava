package com.yangwu.designpattern.strategy.duck;

import com.yangwu.designpattern.strategy.fly.FlyWithWings;
import com.yangwu.designpattern.strategy.quack.Quack;

public class RedHeadDuck extends Duck {

	public RedHeadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("这是一只红头鸭 ！");
	}

}
