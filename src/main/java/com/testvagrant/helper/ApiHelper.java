package com.testvagrant.helper;

import java.util.Map;

import com.testvagrant.utilities.ReportLogger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {

	public ApiHelper() {
	}

	public static Response getResponseWithHeaders(Map<String, String> queryParams, String url) {
		/*RestAssured.baseURI = url;
		
		RequestSpecification spec = RestAssured.given();*/
		
		Response response = RestAssured.given().relaxedHTTPSValidation().queryParams(queryParams).get(url);

		ReportLogger.info("API response : " + response.asString());

		response.then().log().all();

		return response;
	}

}