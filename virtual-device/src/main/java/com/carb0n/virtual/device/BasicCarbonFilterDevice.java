package com.carb0n.virtual.device;

import java.util.concurrent.ThreadLocalRandom;

public class BasicCarbonFilterDevice extends AbstractDevice {

	@Override
	public double getPreFilterCarbon() {
		return getRandom();
	}

	@Override
	public double getPostFilterCarbon() {
		return getRandom();
	}
	
	private double getRandom() {
		double random = ThreadLocalRandom.current().nextDouble(0, 10);
		if (random > 9) {
			return -1; //allowing for a 1 in 10 error condition
		}
		return random;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
