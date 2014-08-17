package com.monolito.pygmify.service;

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
}