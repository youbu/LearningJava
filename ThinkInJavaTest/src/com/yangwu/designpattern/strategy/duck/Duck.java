package com.yangwu.designpattern.strategy.duck;

import com.yangwu.designpattern.strategy.fly.FlyBehavior;
import com.yangwu.designpattern.strategy.quack.QuackBehavior;

public abstract class Duck {
	FlyBehavior flyBehavior;

	QuackBehavior quackBehavior;

//	public void setQuackBehavior(QuackBehavior quackBehavior) {
//		this.quackBehavior = quackBehavior;
//	}
//
//	public void setFlyBehavior(FlyBehavior flyBehavior) {
//		this.flyBehavior = flyBehavior;
//	}

	public void swim() {
		System.out.println("所有的鸭子都会游泳 ！");
	}

	public abstract void display();

	public void performFly() {
		flyBehavior.fly();
	}

	public void performQuack() {
		quackBehavior.qucak();
	}

}
