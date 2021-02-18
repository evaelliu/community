package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

/**
 * Created by yiliu on 31/3/15.
 */
public class IncorrectUserPasswordException extends ApplicationException {
    private String userName;
    private String password;

    public IncorrectUserPasswordException(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
