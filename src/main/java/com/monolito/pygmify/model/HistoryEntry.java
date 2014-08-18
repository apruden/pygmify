package com.monolito.pygmify.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author alex
 *
 */
@Entity
public class HistoryEntry {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 
	 */
	@ManyToOne
	private User user;

	/**
	 * 
	 */
	@ManyToOne
	private Document document;

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 * @return
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * 
	 * @param document
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
}
