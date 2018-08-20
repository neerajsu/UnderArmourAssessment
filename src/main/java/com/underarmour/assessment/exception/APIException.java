package com.underarmour.assessment.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
    private String message;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public APIException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

   
}
