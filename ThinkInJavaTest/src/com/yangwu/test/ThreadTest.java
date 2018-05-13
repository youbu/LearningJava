package com.yangwu.test;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(()->{
			
			while(true) {
			
				System.out.println("Thread " + Thread.currentThread().getName() + " is running!");
			
				long now = System.currentTimeMillis();
				
				while (System.currentTimeMillis() < now + 2000) {}
			}			
		}) ;
		
		t.start();
		Thread.sleep(5000);
		System.out.println(t.isInterrupted());
		t.interrupt();
		System.out.println(t.isInterrupted());
		t.interrupted();
		System.out.println(t.isInterrupted());
	}

}
