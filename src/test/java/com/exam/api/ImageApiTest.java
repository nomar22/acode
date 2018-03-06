////package com.exam.api;
////
////import static junit.framework.TestCase.assertNotNull;
////import static org.junit.Assert.assertEquals;
////
////import javax.management.Notification;
////import javax.ws.rs.client.Entity;
////import javax.ws.rs.core.Application;
////import javax.ws.rs.core.MediaType;
////import javax.ws.rs.core.Response;
////
////import org.glassfish.jersey.server.ResourceConfig;
////import org.glassfish.jersey.test.JerseyTest;
////import org.glassfish.jersey.test.TestProperties;
////import org.junit.Test;
////
////import com.exam.domain.Image;
////import com.exam.domain.Product;
////
//public class ImageApiTest extends FunctionalTest {
//
//
//	@Test
//	public void testFetchAll() {
//		Response output = target("/images").request().get();
//		assertEquals("should return status 200", 200, output.getStatus());
//		assertNotNull("Should return list", output.getEntity());
//	}
//
//	@Test
//	public void testFetchBy() {
//		Response output = target("/images/1").request().get();
//		assertEquals("Should return status 200", 200, output.getStatus());
//		assertNotNull("Should return notification", output.getEntity());
//	}
//
//	// @Test
//	// public void testFetchByFail_DoesNotHaveDigit(){
//	// Response output = target("/images/no-id-digit").request().get();
//	// assertEquals("Should return status 404", 404, output.getStatus());
//	// }
//
//	@Test
//	public void testCreate() {
//		Image notification = new Image(1, new Product());
//		Response output = target("/images").request().post(Entity.entity(notification, MediaType.APPLICATION_JSON));
//
//		assertEquals("Should return status 200", 200, output.getStatus());
//		assertNotNull("Should return notification", output.getEntity());
//	}
//
//	@Test
//	public void testUpdate() {
//		Image notification = new Image(1, new Product());
//		Response output = target("/images").request()
//				.put(Entity.entity(notification, MediaType.APPLICATION_JSON));
//		assertEquals("Should return status 204", 204, output.getStatus());
//	}
//
//	@Test
//	public void testDelete() {
//		Response output = target("/images/1").request().delete();
//		assertEquals("Should return status 204", 204, output.getStatus());
//	}
//
//}