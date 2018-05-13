package com.yangwu.test;

public class InterruptThreadTest3 extends Thread{  
    
    public void run() {  
        // 这里调用的是非清除中断标志位的isInterrupted方法  
        while(!Thread.currentThread().isInterrupted()) {  
            System.out.println(Thread.currentThread().getName() + " is running");  
            try {  
                System.out.println(Thread.currentThread().getName() + " Thread.sleep begin");  
                Thread.sleep(1000);  
                System.out.println(Thread.currentThread().getName() + " Thread.sleep end");  
            } catch (InterruptedException e) {  
                //由于调用sleep()方法清除状态标志位 所以这里需要再次重置中断标志位 否则线程会继续运行下去  
                Thread.currentThread().interrupt();  
                e.printStackTrace();  
            }  
        }  
        if (Thread.currentThread().isInterrupted()) {  
            System.out.println(Thread.currentThread().getName() + "is interrupted");  
        }  
    }  
      
    public static void main(String[] args) {  
        InterruptThreadTest3 itt = new InterruptThreadTest3();  
        itt.start();  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        // 设置线程的中断标志位  
        itt.interrupt();  
    }  
}  
