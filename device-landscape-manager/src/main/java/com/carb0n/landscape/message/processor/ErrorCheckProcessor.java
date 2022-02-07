package com.carb0n.landscape.message.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ErrorCheckProcessor implements IMessageProcessor {
	
	private SimpMessagingTemplate template;
	
	public ErrorCheckProcessor(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	public SensorMessage process(SensorMessage message) throws JsonProcessingException {
		if (message.getPostFilter() < 0 || message.getPreFilter() < 0) {
			message.setStatus(Status.ERROR);
			ObjectMapper objectMapper = new ObjectMapper();
			this.template.convertAndSend("/topic/error", objectMapper.writeValueAsString(message));
		}
		return message;
	}

}
