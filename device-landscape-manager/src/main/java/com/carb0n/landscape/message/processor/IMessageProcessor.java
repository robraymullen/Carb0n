package com.carb0n.landscape.message.processor;

import com.carb0n.landscape.message.model.SensorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IMessageProcessor {
	
	public SensorMessage process(SensorMessage message) throws JsonProcessingException;

}
