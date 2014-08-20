package com.monolito.pygmify.service;

import java.util.concurrent.Future;

/**
 * 
 * @author alex
 *
 */
public interface ScrapperService {

	/**
	 * 
	 * @param url
	 * @return
	 */
	String getContent(String url);
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	Future<String> getContentAsync(String url);
	
}