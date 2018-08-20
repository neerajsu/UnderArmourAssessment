package com.underarmour.assessment.model;

import java.io.Serializable;
import java.util.Date;

public class RedisMessage implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;

	private String text;
	private Date expirationDate;
	

	public RedisMessage(Integer id, String username, String text, Date expirationDate) {
		super();
		this.id = id;
		this.username = username;
		this.text = text;
		this.expirationDate = expirationDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


}
