package com.underarmour.assessment.web;

public class ChatResponseDto {
	String userName;
	String text;
	String expirationDate;

	public ChatResponseDto(String userName, String text, String expirationDate) {
		super();
		this.userName = userName;
		this.text = text;
		this.expirationDate = expirationDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
}
