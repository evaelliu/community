package com.evael.community.account.management.domain.exception;

import com.evael.community.common.exception.ApplicationException;

/**
 * Created by yiliu on 31/3/15.
 */
public class DuplicatedUserException extends ApplicationException {
    private String registerKey;

    public DuplicatedUserException(String registerKey) {
        this.registerKey = registerKey;
    }

    public String getRegisterKey() {
        return registerKey;
    }


    @Override
    public String toString() {
        return "DuplicatedUserException{" +
                "registerKey='" + registerKey + '\'' +
                '}';
    }
}
