package com.underarmour.assessment.exception;

public class ErrorInfo {
    
    private String status;
    private String errorMessage;
    private String errorCode;

    public ErrorInfo(){
    	
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorInfo(String status, String errorMessage, String errorCode) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	
 
}