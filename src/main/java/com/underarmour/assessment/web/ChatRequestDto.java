package com.underarmour.assessment.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChatRequestDto {

	@NotNull
	// @Pattern(regexp = "[a-zA-Z0-9- ]+") -- Example : Not required but would add
	// stuff like this validation of input
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty // I would also add pattern validation to ensure there is no way of messing up
				// security with injection stuff
	private String text;
	private Integer timeout;

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

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public ChatRequestDto(String username, String text, Integer timeout) {
		super();
		this.username = username;
		this.text = text;
		this.timeout = timeout;
	}

}
