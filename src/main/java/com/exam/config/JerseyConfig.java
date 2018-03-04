package com.exam.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.exam.api.ImagesApi;
import com.exam.api.ProductsApi;

/**
 * Class to configure the Jersey Component .
 * @author RafaelRa
 *
 */
@ApplicationPath("/resources")
@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		//Registering all apis to work as a jax-rs
		register(ImagesApi.class);
		register(ProductsApi.class);
		register(JacksonFeature.class);
	}
}
