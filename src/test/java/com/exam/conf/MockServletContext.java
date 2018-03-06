package com.exam.conf;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.support.ServletContextAwareProcessor;

//TODO no idea
@Configuration
public class MockServletContext {

	public static void setServletContext(ServletContext sc) {
		servletContext = sc;
	}

	private static ServletContext getServletContext() {
		return servletContext;
	}

	private static ServletContext servletContext;

	@Configuration
	@Profile("server-test")
	static class ServerTestContext {

		static public @Bean ServletContextAwareProcessor scap() {
			ServletContext sc = getServletContext();
			return new ServletContextAwareProcessor(sc);
		}
	}
}