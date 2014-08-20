package com.monolito.pygmify.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.monolito.pygmify.model.Document;
import com.monolito.pygmify.model.HistoryEntry;
import com.monolito.pygmify.model.User;

import org.apache.log4j.Logger;

/**
 * 
 * @author alex
 * 
 */
@Service
public class ScrapperServiceImpl implements ScrapperService {

	static Logger log = Logger.getLogger(ScrapperServiceImpl.class.getName());

	@PersistenceContext
	EntityManager em;

	@Autowired
	@Qualifier(value="simpleBean")
	HttpClient client;
	
	/**
	 * 
	 */
	@Override
	public String getContent(final String url) {
		log.error("getContent impl called");
		return getContentInternal(url);
	}

	/**
	 * 
	 */
	@Async
	@Override
	@Transactional
	public Future<String> getContentAsync(final String url) {
		log.error("getContent impl called");
		String res = getContentInternal(url);

		return new AsyncResult<String>(res);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	private String getContentInternal(String url) {
		log.error("getting content");
		TypedQuery<Document> query = em.createQuery(
				"from Document d where d.url = :url", Document.class);
		query.setParameter("url", url);
		List<Document> docs = (List<Document>) query.getResultList();

		if (!docs.isEmpty()) {
			Document doc = docs.get(0);
			log.debug("found document " + doc);

			return doc.getContent();
		}

		log.debug("document not found");

		String content = client.get(url);
		org.jsoup.nodes.Document htmlDoc = Jsoup.parse(content);
		content = htmlDoc.text();

		Document doc = new Document(url, content.substring(0,
				Math.min(70000, content.length())));
		User u = new User();
		HistoryEntry e = new HistoryEntry();
		e.setDocument(doc);
		e.setUser(u);
		em.persist(doc);
		em.persist(u);
		em.persist(e);
		log.debug("saved " + doc.toString());

		return content;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getDynamicPage(String url) {
		final WebClient webClient = new WebClient();
		HtmlPage page;

		try {
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setActiveXNative(false);
			webClient.getOptions().setAppletEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setUseInsecureSSL(true);
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			return "";
		}

		return page.asText();
	}
}