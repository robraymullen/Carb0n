package com.carb0nAnalysis.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SensorData {
	
	@Id
	private String id;
	
	private double preFilter;
	
	private double postFilter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPreFilter() {
		return preFilter;
	}

	public void setPreFilter(double preFilter) {
		this.preFilter = preFilter;
	}

	public double getPostFilter() {
		return postFilter;
	}

	public void setPostFilter(double postFilter) {
		this.postFilter = postFilter;
	}

}
