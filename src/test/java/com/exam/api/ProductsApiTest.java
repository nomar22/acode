//package com.exam.api;
//
//import javax.ws.rs.core.Response;
//
//import org.glassfish.jersey.test.JerseyTest;
//import org.junit.Test;
//
//public class ProductsApiTest extends JerseyTest {
//
//
////	@Override
////	protected Application configure() {
//////		return new ResourceConfig(ProductsApi.class);
////	}
//
//	@Test
//	public void test() {
//		Response response = target("products").request().get();
//		String hello = response.readEntity(String.class);
////		assertEquals("Hello World!", hello);
//		response.close();
//	}
//}