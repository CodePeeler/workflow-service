package com.eventhorizon.nova.msgconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eventhorizon.nova.msghandlers.Consumer;
import com.eventhorizon.nova.msghandlers.Producer;

@Configuration
public class MessageConfig {

	static final String topicExchangeName = "workflow-exchange";
	static final String orderQueue = "orders";
	static final String orderRoutingKey = "customer.order.#";

	@Bean
	Queue queue() {
		return new Queue(orderQueue, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(orderRoutingKey);
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory("localhost");
	}

	@Bean
	RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	Producer producer() {
		return new Producer(rabbitTemplate(), topicExchangeName, orderRoutingKey);
	}

	@Bean
	Consumer consumer() {
		return new Consumer();
	}

	@Bean
	MessageListenerAdapter listenerAdapter() {
		return new MessageListenerAdapter(consumer(), "getMessage");
	}

	@Bean
	SimpleMessageListenerContainer container() {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueueNames(orderQueue);
		container.setMessageListener(listenerAdapter());
		return container;
	}

}
