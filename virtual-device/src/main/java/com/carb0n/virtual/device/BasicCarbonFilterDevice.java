package com.carb0n.virtual.device;

import java.util.concurrent.ThreadLocalRandom;

public class BasicCarbonFilterDevice extends AbstractDevice {
	
	private double preFilter = getRandom();

	@Override
	public double getPreFilterCarbon() {
		this.preFilter = getRandom();
		return this.preFilter;
	}

	@Override
	public double getPostFilterCarbon() {
		double postFilter;
		if (this.preFilter < 0) {
			postFilter = -1;
		} else {
			postFilter = this.preFilter - ThreadLocalRandom.current().nextDouble(0, this.preFilter);
		}
		return postFilter;
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
