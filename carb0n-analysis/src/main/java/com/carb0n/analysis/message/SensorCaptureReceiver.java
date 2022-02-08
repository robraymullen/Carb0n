package com.carb0n.analysis.message;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carb0n.analysis.message.model.SensorMessage;
import com.carb0n.analysis.repository.ISensorDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SensorCaptureReceiver {
	
	@Autowired
	private ISensorDataRepository repository;
	
	public void receiveMessage(byte[] array) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			SensorMessage message = mapper.readValue(array, SensorMessage.class);
			if (isValid(message)) {
				repository.save(message);
				System.out.println(repository.findById(message.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isValid(SensorMessage message) {
		return message.getPostFilter() > 0 && message.getPreFilter() > 0;
	}

}
