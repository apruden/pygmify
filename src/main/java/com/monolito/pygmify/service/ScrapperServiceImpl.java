package com.monolito.pygmify.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.monolito.pygmify.model.Document;
/*import com.monolito.pygmify.model.HistoryEntry;
 import com.monolito.pygmify.model.User;*/

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

	/**
	 * 
	 */
	@Override
	@Transactional
	public String getContent(String url) {
		log.debug("getting content");
		TypedQuery<Document> query = em.createQuery(
				"from Document d where d.url = :url", Document.class);
		query.setParameter("url", url);
		List<Document> docs = (List<Document>) query.getResultList();

		if (!docs.isEmpty()) {
			Document doc = docs.get(0);
			log.info("found document " + doc);

			return doc.getContent();
		}

		log.debug("not found document");

		final WebClient webClient = new WebClient();
		HtmlPage page;

		try {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setUseInsecureSSL(true);
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			return "ERROR";
		}

		log.debug("about to save");
		final String pageAsText = page.asText();
		Document doc = new Document(url, pageAsText);
		/*
		 * User u = new User(); HistoryEntry e = new HistoryEntry();
		 * e.setDocument(doc); e.setUser(u);
		 */
		em.persist(doc);
		/*
		 * em.persist(u); em.persist(e);
		 */

		log.debug("saved " + doc.toString());

		return pageAsText;
	}
}