package com.evael.community.common.dto;

import java.io.Serializable;

//@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResponseDTO implements Serializable {
    private boolean success;
    private int errorCode;
    private String message;
    private Object data;

    /* constructors */

    public ResponseDTO() {
        this.success = true;
    }

    public ResponseDTO(boolean success, int errorCode, String message, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(int errorCode, String message) {
        this(false, errorCode, message, null);
    }

    public ResponseDTO(int errorCode) {
        this(false, errorCode, "", null);
    }

    public ResponseDTO(Object data) {
        this(true, 0, "", data);
    }

    public ResponseDTO(boolean success) {
        this(success, 0, "", null);
    }

    /* getter and setters */

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
