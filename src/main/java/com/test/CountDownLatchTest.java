package com.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	public static void main(String[] args) {
		
		final CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(1);
		
		for(int i=0;i<10;i++){
			
		}
		new Thread(new Runnable() {
			public void run() {
				try {
					begin.await();
					System.out.println(33);
					System.out.println(44);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		begin.countDown();
		System.out.println("end");
	}
}
