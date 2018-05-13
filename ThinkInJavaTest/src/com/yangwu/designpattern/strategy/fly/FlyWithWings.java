package com.yangwu.designpattern.strategy.fly;

public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("Fly with wings !");
	}

}
