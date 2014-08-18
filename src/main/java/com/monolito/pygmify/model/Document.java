package com.monolito.pygmify.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author alex
 *
 */
@Entity
public class Document {

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
	@Column(length=1000)
	private String url;

	/**
	 * 
	 */
	@Column(length=100000)
	private String content;

	/**
	 * 
	 */
	public Document() {
	}

	/**
	 * 
	 * @param url
	 * @param content
	 */
	public Document(String url, String content) {
		this.url = url;
		this.content = content;
	}

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
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "<Document " + this.id + " at " + this.url + ">";
	}
}
