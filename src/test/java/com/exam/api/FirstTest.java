package com.exam.api;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;
import com.jayway.restassured.RestAssured;

public class FirstTest extends FunctionalTest {

	@Test
	public void testGetProductExcludingRelationships() {
		given().when().get("/products/nochild/{id}", 1).then().statusCode(200);
	}

	@Test
	public void invalidParkingSpace() {
		RestAssured.given().when().get("/garage/slots/999").then().statusCode(404);
	}

}
