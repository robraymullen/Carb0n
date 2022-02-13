package com.carb0nAnalysis.message;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carb0nAnalysis.model.SensorData;
import com.carb0nAnalysis.repository.ISensorDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SensorCaptureReceiver {

	@Autowired
	private ISensorDataRepository repository;

	public void receiveMessage(byte[] array) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			SensorData message = mapper.readValue(array, SensorData.class);
			if (isValid(message)) {
				repository.save(message);
				System.out.println(repository.findById(message.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isValid(SensorData message) {
		return message.getPostFilter() > 0 && message.getPreFilter() > 0;
	}

}