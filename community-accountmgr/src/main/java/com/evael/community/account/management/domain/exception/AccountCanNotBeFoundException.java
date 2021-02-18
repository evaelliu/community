package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

/**
 * Created by shengw on 2015/2/25.
 */
public class AccountCanNotBeFoundException extends ApplicationException {
    private String userKey;

    public AccountCanNotBeFoundException(String userKey) {
        this.userKey= userKey;
    }

    public String getUserKey() {
        return userKey;
    }
}
