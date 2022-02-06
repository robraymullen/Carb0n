package com.carb0n.landscape.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorMessage {
	
	public enum Status {
		UNPROCESSED,
		ERROR,
		PROCESSED,
	}

	private Status status;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("preFilter")
	private double preFilter;
	
	@JsonProperty("postFilter")
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "SensorMessage [status=" + status + ", id=" + id + ", preFilter=" + preFilter + ", postFilter="
				+ postFilter + "]";
	}
}
