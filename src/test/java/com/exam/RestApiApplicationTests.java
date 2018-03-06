package com.exam;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.boot.test.context.SpringBootTest;

@ApplicationPath("/v1")
@SpringBootTest
public class RestApiApplicationTests extends Application {
	
	

	@Override
	public Map<String, Object> getProperties() {
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("jersey.config.server.provider.packages", "com.memorynotfound.rs");
		return properties;
	}
}