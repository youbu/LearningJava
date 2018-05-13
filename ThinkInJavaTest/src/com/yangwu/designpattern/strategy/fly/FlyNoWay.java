package com.yangwu.designpattern.strategy.fly;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println(" I can't fly !");
	}

}
