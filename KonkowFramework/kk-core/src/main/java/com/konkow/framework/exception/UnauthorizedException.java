package com.konkow.framework.exception;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = -5297055876757537233L;
    
    private String errorCode;
	private String errorMessage;
	
	public UnauthorizedException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
