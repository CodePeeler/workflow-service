package com.eventhorizon.nova.msghandlers;

import java.util.concurrent.CountDownLatch;

public class Consumer {

	private CountDownLatch latch = new CountDownLatch(1);

	public void getMessage(String message) {
		System.out.println("Received <" + message + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}

