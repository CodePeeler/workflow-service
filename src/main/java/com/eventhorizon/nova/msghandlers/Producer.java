package com.eventhorizon.nova.msghandlers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Producer {

	private RabbitTemplate rabbitTemplate;
	private String topicExchangeName;
	private String routingKey;

	public Producer(RabbitTemplate rabbitTemplate, 
			String topicExchangeName, String routingKey) {

		this.rabbitTemplate = rabbitTemplate;
		this.topicExchangeName = topicExchangeName;
		this.routingKey = routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public void sendMessage(String message) {
		rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
	}

}
