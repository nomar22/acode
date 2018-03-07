package com.exam.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.domain.Image;
import com.exam.domain.Product;
import com.jayway.restassured.http.ContentType;

//import static org.hamcrest.Matchers.equalTo;
//
//import org.junit.Test;
//
//import com.avenuecode.test.util.ImageGenerator;
//import com.avenuecode.test.util.ProductGenerator;
//import com.exam.domain.Product;
//import com.jayway.restassured.http.ContentType;
//

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsApiTest extends FunctionalTest {

	@Override
	protected String configureSubDomain() {
		return "products";
	}

	@Test
	public void testGetProductExcludingRelationships() {
		given().when().get("nochild/{id}", 1).then().statusCode(Response.Status.OK.getStatusCode());
	}

	@Test
	public void testGetAllProducts() {
		given().when().get().then().statusCode(Response.Status.OK.getStatusCode());
	}

	@Test
	public void testGetProductWithFetch() {
		given().when().get("{id}", 1).then().body("name", equalTo("Computer")).statusCode(Response.Status.OK.getStatusCode());
	}

	@Test
	public void testSave() {

		Set<Image> images = new HashSet<>();
		images.add(new Image());
		images.add(new Image());
		images.add(new Image());

		Product product = new Product.ProductBuilder(null, "Product 5").setImages(images).build();
		//
		given().contentType(ContentType.JSON).body(product).when().post().then().body("name", equalTo("Product 5"))
				.statusCode(Response.Status.OK.getStatusCode());
	}
	
	@Test
	public void testUpdate() {
		Product product = new Product.ProductBuilder(1, "Black Computer").build();

		given().contentType(ContentType.JSON).body(product).when().put("{id}", product.getId().toString()).then()
				.body("name", equalTo("Black Computer")).statusCode(Response.Status.OK.getStatusCode());
	}
	
	@Test
	public void testDelete() {
		given().when().delete("{id}", 1).then().statusCode(Response.Status.OK.getStatusCode());
	}
	@Test
	public void testDeleteFail() {
		given().when().delete("{id}", 2).then().statusCode(403);
	}
	
	
	//

}
////