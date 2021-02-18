package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

/**
 * Created by shengw on 2015/2/25.
 */
public class AccountNotExistException extends ApplicationException {

    Long accountId;
    String username;

    public AccountNotExistException(Long accountId) {
        this.accountId = accountId;
    }

    public AccountNotExistException(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AccountNotExistException{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                '}';
    }
}
