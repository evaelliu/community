package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

/**
 * @Author jiyou
 * @Date  2016/3/29.
 */
public class FailToValidateAuthCodeException extends ApplicationException{
    private String key;
    private String code;

    public FailToValidateAuthCodeException(String key, String code) {
        this.key = key;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{" +
                "'key':'" + key + '\'' + "," +
                "'code':'" + code + '\'' +
                '}';
    }
}
