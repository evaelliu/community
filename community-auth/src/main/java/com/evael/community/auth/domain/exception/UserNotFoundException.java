package com.evael.community.auth.domain.exception;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * @author Guitar
 */
public class UserNotFoundException extends BadCredentialsException {

    private String userName;

    public UserNotFoundException(String userName) {
        super("user not exists : " + userName);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "{" +
                "'userName':'" + userName + '\'' +
                '}';
    }
}
