package com.evael.community.common.exception;

/**
 * @author Guitar
 */
public class InvalidParamException extends ApplicationException {
    private String errorMessage;

    public InvalidParamException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "errorMessage='" + errorMessage + '\'';
    }
}
