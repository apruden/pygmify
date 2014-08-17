package com.monolito.pygmify.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.monolito.pygmify.resource.HistoryResource;

/**
 * 
 * @author alex
 *
 */
public class PygmifyApplication extends ResourceConfig {

	/**
	 * 
	 */
	public PygmifyApplication() {
		register(RequestContextFilter.class);
		register(HistoryResource.class);
		register(JacksonFeature.class);
	}
}
