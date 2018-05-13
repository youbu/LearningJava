package com.yangwu.test;

public class Thread3 extends Thread{
    public void run(){  
        while(true){  
            if(Thread.currentThread().isInterrupted()){  
                System.out.println("Someone interrupted me.");  
            }  
            else{  
                System.out.println("Thread is Going...");  
            }
        }  
    }  

    public static void main(String[] args) throws InterruptedException {  
        Thread3 t = new Thread3();  
        t.start();  
        Thread.sleep(3000);  
        t.interrupt();  
    }  
}  