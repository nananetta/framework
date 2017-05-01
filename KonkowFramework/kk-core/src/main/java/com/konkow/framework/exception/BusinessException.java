package com.konkow.framework.exception;

import com.konkow.framework.ErrorProperties;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1646832541786063031L;

    private String errorCode;
    private String errorMessage;

    public BusinessException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorPropertyName) {
        this.errorCode = ErrorProperties.get(errorPropertyName + ".code");
        this.errorMessage = ErrorProperties.get(errorPropertyName + ".text");
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
