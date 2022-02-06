package com.carb0n.landscape.message.processor;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MessageProcessPipeline {
	
	Queue<IMessageProcessor> pipeline = new ArrayDeque<>();
	
	Map<String, SensorMessage> errorMessages = new HashMap<>();
	Map<String, SensorMessage> successMessages = new HashMap<>();
	
	private SimpMessagingTemplate template;
	
	public MessageProcessPipeline(SimpMessagingTemplate template) {
		this.template = template;
		pipeline.add(new ErrorCheckProcessor(template));
		pipeline.add(new SuccessProcessor(template));
	}
	
	public void run(SensorMessage message) {
		while (message.getStatus() != Status.ERROR && message.getStatus() != Status.PROCESSED) {
			IMessageProcessor processor = pipeline.poll();
			try {
				message = processor.process(message);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			if (message.getStatus() == Status.ERROR) errorMessages.put(message.getId(), message);
			else successMessages.put(message.getId(), message);
			System.out.println("In pipeline: "+message);
		}
	}
	
	public Map<String, SensorMessage> getErrorMessages() {
		return this.errorMessages;
	}
	
	public Map<String, SensorMessage> getSuccessMessages() {
		return this.successMessages;
	}
}
