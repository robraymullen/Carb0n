package com.carb0n.virtual.device;

public interface IDevice extends Runnable {
	
	public double getPreFilterCarbon();
	
	public double getPostFilterCarbon();
	
	public void setId(String id);

}
