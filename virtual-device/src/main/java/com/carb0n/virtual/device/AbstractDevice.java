package com.carb0n.virtual.device;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class AbstractDevice implements IDevice {
	
	protected String id;
	
	private static final String EXCHANGE_NAME = "sensor-data";
	
	public void run() {
		while (true) {
			ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
		        channel.exchangeDeclare(EXCHANGE_NAME, "fanout", true);

		        JSONObject json = new JSONObject();
		        json.put("preFilter", this.getPreFilterCarbon());
		        json.put("postFilter", this.getPostFilterCarbon());
		        json.put("id", this.id);
		        String message = json.toString();
		        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
		        System.out.println(" [x] Sent '" + message + "'");
		        long sleep = (long) (Math.random() * 10000);
		        Thread.sleep(sleep);
		    } catch (Exception e) {
		    	e.printStackTrace();
			}	
		}
	}

}
