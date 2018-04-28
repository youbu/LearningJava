package com.yangwu.sample.exception;

public abstract class Inning {
	public Inning() throws BaseballException{
		
	}
	
	public void event() throws BaseballException {
		
	}
	
	public abstract void atBat() throws Foul,Strike;
	
	public void walk() {
		
	}
}
