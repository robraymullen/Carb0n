package com.carb0n.landscape.message.processor;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;

@Controller
public class ErrorCheckProcessor implements IMessageProcessor {
	
	@CrossOrigin
	@SendTo("/topic/error")
	public SensorMessage process(SensorMessage message) {
		if (message.getPostFilter() < 0 || message.getPreFilter() < 0) {
			message.setStatus(Status.ERROR);
		}
		return message;
	}

}
