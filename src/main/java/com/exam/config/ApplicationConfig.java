package com.exam.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.exam.api.ImagesApi;
import com.exam.api.ProductsApi;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Class to configure the Jersey Component and Swagger.
 * @author RafaelRa
 *
 */
@ApplicationPath("/v1")
@Component
public class ApplicationConfig extends ResourceConfig {
	public ApplicationConfig() {
		//Registering all apis to work as a jax-rs
		register(ImagesApi.class);
		register(ProductsApi.class);
		register(JacksonFeature.class);
		
		//Swagger
		register(ApiListingResource.class);
		register(SwaggerSerializers.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
		
		BeanConfig conf = new BeanConfig();
		conf.setTitle("Product Image API");
		conf.setDescription("Product and Image Relationship");
		conf.setVersion("1.0.0");
		conf.setHost("localhost:8080");
		conf.setBasePath("/v1");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("com.exam.api");
		conf.setScan(true);
		
	}
}
