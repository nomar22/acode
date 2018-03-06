package com.exam.api;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class FirstTest extends FunctionalTest {

	@Test
	public void testGetProductExcludingRelationships() {
		given().when().get("/products/nochild/{id}", 1).then().statusCode(200);
	}


}
