package com.carb0n.landscape.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class DeviceConfigController {
	
	private List<String> virtualIds = new ArrayList<>();
	
	public DeviceConfigController() {
		int count = 10;
		int i = 0;
		while (i<count) {
			virtualIds.add(UUID.randomUUID().toString());
			i++;
		}
	}
	
	@GetMapping("/config/virtual/ids")
	public List<String> getVirtualIds() {
		return virtualIds;
	}

}
