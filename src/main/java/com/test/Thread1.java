package com.test;

public class Thread1 {
	

	public static void main(String[] args) {
		Buss b = new Buss();
		Buss b1 = new Buss();
		Thread t1 = new Thread(b1);
		Thread t2 = new Thread(b1);
		t1.start();
		t2.start();
	}
}
