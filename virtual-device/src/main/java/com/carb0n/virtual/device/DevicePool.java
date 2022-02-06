package com.carb0n.virtual.device;

import java.util.ArrayList;
import java.util.List;

public class DevicePool {
	
	List<IDevice> devicePool = new ArrayList<>();
	
	public void startEmulation() {
		int i = 0;
		while (i < 10) {
			devicePool.add(new BasicCarbonFilterDevice());
			i++;
		}
		devicePool.forEach((device) -> {
			Thread t = new Thread(device);
			t.start();
		});
	}

}
