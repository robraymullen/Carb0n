package com.carb0n.analysis;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.carb0n.analysis.message.SensorCaptureReceiver;

@EnableJpaRepositories("com.carb0n.analysis.repository")
@ComponentScan("com.carb0n.analysis.message.model") 
@SpringBootApplication
public class Carb0nAnalysisApplication {
	
	private String queueName = "sensor-data-filter-analysis";
	
	private String topicExchangeName = "sensor-data";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(SensorCaptureReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) {
		SpringApplication.run(Carb0nAnalysisApplication.class, args);
	}

}
