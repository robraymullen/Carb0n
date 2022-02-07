package com.carb0n.landscape.message.processor;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SuccessProcessor implements IMessageProcessor {

	private SimpMessagingTemplate template;

	public SuccessProcessor(SimpMessagingTemplate template) {
		this.template = template;
	}

	public SensorMessage process(SensorMessage message) throws JsonProcessingException {
		if (message.getPostFilter() > -1 && message.getPreFilter() > -1) {
			message.setStatus(Status.PROCESSED);
			ObjectMapper objectMapper = new ObjectMapper();
			this.template.convertAndSend("/topic/success", objectMapper.writeValueAsString(message));
		}
		return message;
	}

}
