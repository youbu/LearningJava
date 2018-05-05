package com.yangwu.test;

public class LamdaTest {
	private  int index = 0;
	
	public int increaseIndex() {
		return ++index ;
	}
	
	public int getIndex() {
		return index;
	}
	public static void main(String[] args) throws Exception {
		final LamdaTest test = new LamdaTest();
//		ReentrantLock lock = new ReentrantLock();
		new Thread(()->{
			for (int i = 0; i < 5000; i++) {
				test.increaseIndex();				
			}
			
		}) .start();
		new Thread(()->{
			for (int i = 0; i < 5000; i++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				test.increaseIndex();					
			}
			
		}) .start();
		
		Thread.sleep(11500);
		System.out.println(test.getIndex());
	}

}
