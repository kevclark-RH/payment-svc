package com.example.payment.tools;

import java.util.Random;

public class GenerateLatency {
	
	private static Random rn = new Random();

	public void confirm() throws InterruptedException{
		Thread.sleep(rn.nextInt(70)+40);
	}
	
	public void update() throws InterruptedException{
		Thread.sleep(rn.nextInt(90)+10);
	}
	
	public void renew() throws InterruptedException{
		Thread.sleep(rn.nextInt(150)+30);
	}
}
