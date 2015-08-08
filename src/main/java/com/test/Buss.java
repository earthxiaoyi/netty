package com.test;

public class Buss implements Runnable{

	public void run() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName());
			System.out.println("33");
		}
	}

}
