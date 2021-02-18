package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

/**
 * @Author jiyou
 * @Date  2016/3/29.
 */
public class FailToRegisterUserException extends ApplicationException{
    private String userName;
    private Long userDBId;

    public FailToRegisterUserException(String userName, Long userDBId) {
        this.userName = userName;
        this.userDBId = userDBId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserDBId() {
        return userDBId;
    }

}
