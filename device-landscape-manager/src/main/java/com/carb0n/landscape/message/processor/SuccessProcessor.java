package com.carb0n.landscape.message.processor;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class SuccessProcessor implements IMessageProcessor {

	private SimpMessagingTemplate template;

	public SuccessProcessor(SimpMessagingTemplate template) {
		this.template = template;
	}

	public SensorMessage process(SensorMessage message) throws JsonProcessingException {
		if (message.getPostFilter() > -1 && message.getPreFilter() > -1) {
			message.setStatus(Status.PROCESSED);
		}
		this.template.convertAndSend("/topic/success", message.toString());
		return message;
	}

}
