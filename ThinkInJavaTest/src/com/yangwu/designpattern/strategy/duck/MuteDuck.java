package com.yangwu.designpattern.strategy.duck;

import com.yangwu.designpattern.strategy.fly.FlyWithWings;
import com.yangwu.designpattern.strategy.quack.Mutequack;

public class MuteDuck extends Duck {

	public MuteDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Mutequack();
	}
	
	@Override
	public void display() {
		System.out.println("ÑÆ°ÍÑ¼×Ó£¡");
	}

}
