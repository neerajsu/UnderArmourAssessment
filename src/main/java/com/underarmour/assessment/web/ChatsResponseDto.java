package com.underarmour.assessment.web;

public class ChatsResponseDto {
	Integer id;
	String text;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ChatsResponseDto(Integer id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

}
