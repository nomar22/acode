package com.exam.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

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
public class ProductsApiTest extends FunctionalTest {

	@Override
	protected String configureSubDomain() {
		return "products";
	}

	
	@Test
	public void testGetProductExcludingRelationships() {
		given().when().get("nochild/{id}", 1).then().statusCode(200);
	}
	
	@Test
	public void testGetAllProducts() {
		given().when().get().then().statusCode(200);
	}
	
	@Test
	public void testGetProductWithFetch() {
		given().when().get("{id}", 1).then().body("name", equalTo("Computer")).statusCode(200);
	}



	@Test
	public void testSaveProduct() {

		Set<Image> images = new HashSet<>();
		images.add(new Image());
		images.add(new Image());
		images.add(new Image());

		Product product = new Product.ProductBuilder(null, "Product 5").setImages(images).build();
		//
		given().contentType(ContentType.JSON).body(product).when().post().then().body("name", equalTo("Product 5"))
				.statusCode(200);
		//
		// product = ProductGenerator.createNewProduct("Product #4", "Product #4
		// description - Child of Product #3",
		// ProductGenerator.createNewProduct(3));
		// product = ImageGenerator.createImageAssociateProduct(product, "Type 5");
		//
		// given().queryParam("parentId",
		// 1).contentType(ContentType.JSON).body(product).when().post().then()
		// .body("name", equalTo("Product #4")).statusCode(200);
	}
	//

}
////