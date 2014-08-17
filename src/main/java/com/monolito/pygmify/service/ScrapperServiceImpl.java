package com.monolito.pygmify.service;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.monolito.pygmify.model.Document;

/**
 * 
 * @author alex
 * 
 */
@Service
public class ScrapperServiceImpl implements ScrapperService {

    @PersistenceContext
    EntityManager em;
	
	/**
	 * 
	 */
	@Override
	public String getContent(String url) {
		final WebClient webClient = new WebClient();
		HtmlPage page;

		try {
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			return "ERROR";
		}

		final String pageAsText = page.asText();
		em.persist(new Document(url, pageAsText));

		return pageAsText;
	}
}