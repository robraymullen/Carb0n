package com.carb0n.landscape.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.carb0n.landscape.message.model.SensorMessage;
import com.carb0n.landscape.message.model.SensorMessage.Status;
import com.carb0n.landscape.message.processor.MessageProcessPipeline;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SensorCaptureReceiver {
	
	private SimpMessagingTemplate template;

    @Autowired
    public SensorCaptureReceiver(SimpMessagingTemplate template) {
        this.template = template;
    }
	
	public void receiveMessage(byte[] array) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		SensorMessage message = mapper.readValue(array, SensorMessage.class);
		message.setStatus(Status.UNPROCESSED);
		MessageProcessPipeline pipeline = new MessageProcessPipeline(template);
		pipeline.run(message);
	}

}
