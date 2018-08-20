package com.underarmour.assessment.web;

public class ChatResponseDto {
	String username;
	String text;
	String expiration_date;

	public ChatResponseDto(String username, String text, String expiration_date) {
		super();
		this.username = username;
		this.text = text;
		this.expiration_date = expiration_date;
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

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	
}
