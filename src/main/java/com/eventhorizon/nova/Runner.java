package com.eventhorizon.nova;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.eventhorizon.nova.msghandlers.Producer;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private Producer producer;

	@Override
	public void run(String... args) throws Exception {

		producer.sendMessage("Create Customer Order");

		//Consumer is wired up to listen in MessageConfig.

	}

}
