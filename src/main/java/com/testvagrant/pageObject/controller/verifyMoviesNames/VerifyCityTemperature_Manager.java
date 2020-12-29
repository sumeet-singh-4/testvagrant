package com.testvagrant.pageObject.controller.verifyMoviesNames;

import java.util.HashMap;
import java.util.Map;

import com.testvagrant.helper.ApiHelper;

import io.restassured.response.Response;

public class VerifyCityTemperature_Manager extends ApiHelper {

	public Float validateTestCase(String server, String cityName, String unit) {

		Response getResponse = getResponse(server, cityName, unit);

		return getResponse.jsonPath().get("main.temp");
	}

	public Response getResponse(String server, String cityName, String unit) {
		String serviceURL = server;

		System.out.println(serviceURL);

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("q", cityName);
		queryParams.put("units", unit);
		queryParams.put("appid", "7fe67bf08c80ded756e598d6f8fedaea");

		Response response = getResponseWithHeaders(queryParams, server);

		return response;

	}
}
