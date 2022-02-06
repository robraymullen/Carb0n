package com.carb0n.landscape.message.processor;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;

public class MessageProcessPipeline {
	
	Queue<IMessageProcessor> pipeline = new ArrayDeque<>();
	
	Map<String, SensorMessage> errorMessages = new HashMap<>();
	Map<String, SensorMessage> successMessages = new HashMap<>();
	
	public MessageProcessPipeline() {
		pipeline.add(new ErrorCheckProcessor());
		pipeline.add(new SuccessProcessor());
	}
	
	public void run(SensorMessage message) {
		while (message.getStatus() != Status.ERROR && message.getStatus() != Status.PROCESSED) {
			IMessageProcessor processor = pipeline.poll();
			message = processor.process(message);
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
