package com.monolito.pygmify.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.log4j.Logger;

/**
 * 
 * @author alex
 *
 */
public class HttpClient {

	static Logger log = Logger.getLogger(HttpClient.class.getName());

	CloseableHttpAsyncClient httpclient;

	/**
	 * 
	 */
	public HttpClient() {
		log.debug("init client");
		this.httpclient = HttpAsyncClients.createDefault();
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public String get(String url) {
		log.debug("client getting");
		this.httpclient.start();
		HttpGet request = new HttpGet(url);
		Future<HttpResponse> future = httpclient.execute(request, null);
		String content = "";

		try {
			HttpResponse response = future.get();
			try (InputStream is = response.getEntity().getContent();
					Scanner s = new Scanner(is).useDelimiter("\\A")) {
				content = s.hasNext() ? s.next() : "";
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		return content;
	}
}
