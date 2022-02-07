package com.carb0n.virtual.device;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class DevicePool {

	List<IDevice> devicePool = new ArrayList<>();

	private final static HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1)
			.followRedirects(Redirect.NORMAL).connectTimeout(Duration.ofSeconds(60)).build();

	private static HttpResponse<String> response;
	
	private String loadPage(String url) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
		response = client.send(request, BodyHandlers.ofString());
		return response.body();
	}

	public void startEmulation() throws Exception {
		JSONArray json = new JSONArray(loadPage("http://localhost:7070/config/virtual/ids"));
		json.forEach(id -> {
			String strId = (String) id;
			IDevice device = new BasicCarbonFilterDevice();
			device.setId(strId);
			devicePool.add(device);
		});
		devicePool.forEach((device) -> {
			Thread t = new Thread(device);
			t.start();
		});
	}

}
