package com.yangwu.test;

public class InterruptThreadTest3 extends Thread{  
    
    public void run() {  
        // ������õ��Ƿ�����жϱ�־λ��isInterrupted����  
        while(!Thread.currentThread().isInterrupted()) {  
            System.out.println(Thread.currentThread().getName() + " is running");  
            try {  
                System.out.println(Thread.currentThread().getName() + " Thread.sleep begin");  
                Thread.sleep(1000);  
                System.out.println(Thread.currentThread().getName() + " Thread.sleep end");  
            } catch (InterruptedException e) {  
                //���ڵ���sleep()�������״̬��־λ ����������Ҫ�ٴ������жϱ�־λ �����̻߳����������ȥ  
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
        // �����̵߳��жϱ�־λ  
        itt.interrupt();  
    }  
}  
