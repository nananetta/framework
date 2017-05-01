package com.konkow.framework.exception;

public class BusinessSQLException extends RuntimeException {

    private static final long serialVersionUID = 1646832541786063031L;

    private String errorCode;
    private String errorMessage;

    public BusinessSQLException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessSQLException(int errorCode, String errorMessage) {
        this.errorCode = Integer.toString(errorCode);
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
