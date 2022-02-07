package com.carb0n.virtual;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.carb0n.virtual.device.DevicePool;

public class VirtualDeviceManager {

	public static void main(String[] args) throws Exception {
		DevicePool pool = new DevicePool();
		pool.startEmulation();
	}

}
