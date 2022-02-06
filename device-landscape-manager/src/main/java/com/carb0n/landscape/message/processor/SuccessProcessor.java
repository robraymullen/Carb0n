package com.carb0n.landscape.message.processor;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;

@Controller
public class SuccessProcessor implements IMessageProcessor {

	@Override
	@SendTo("/topic/success")
	public SensorMessage process(SensorMessage message) {
		if (message.getPostFilter() > -1 && message.getPreFilter() > -1) {
			message.setStatus(Status.PROCESSED);
		}
		return message;
	}

}
