package com.exam.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.domain.Image;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageApiTest extends FunctionalTest {

	@Override
	protected String configureSubDomain() {
		return "images";
	}

	@Test
	public void testSave() {
		Image image = new Image(null, null, "Window");

		given().contentType(ContentType.JSON).body(image).when().post().then().statusCode(Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void testUpdate() {
		Image image = new Image(1, null, "Smartphone");
		given().contentType(ContentType.JSON).body(image).when().put("{id}", image.getId()).then()
				.body("description", equalTo("Smartphone")).statusCode(Response.Status.OK.getStatusCode());
	}

	@Test
	public void testDeleteImage() {
		given().when().delete("{id}", 1).then().statusCode(Response.Status.OK.getStatusCode());
	}

	@Test
	public void testGetProductImages() {
		given().when().get("productImages/{id}", 1).then().statusCode(Response.Status.OK.getStatusCode());
	}

}
////