package com.carb0n.landscape.message.processor;

import com.carb0n.landscape.message.model.SensorMessage;

public interface IMessageProcessor {
	
	public SensorMessage process(SensorMessage message);

}
