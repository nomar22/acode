package com.exam.api;

import org.junit.Before;
import org.springframework.boot.context.embedded.LocalServerPort;

import com.jayway.restassured.RestAssured;

public abstract class FunctionalTest {

	@LocalServerPort
	private int port;

	@Before
	public void setup() {

		RestAssured.port = port;

		String basePath = System.getProperty("server.base");
		if (basePath == null) {
			basePath = "/v1/";
		}
		RestAssured.basePath = basePath + configureSubDomain() + "/";

		String baseHost = System.getProperty("server.host");
		if (baseHost == null) {
			baseHost = "http://localhost";
		}
		RestAssured.baseURI = baseHost;

	}

	/**
	 * Set subdomain without slashs
	 * 
	 * @return
	 */
	protected abstract String configureSubDomain();

}