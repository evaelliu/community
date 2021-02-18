package com.evael.community.auth.domain.exception;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * @author Guitar
 */
public class InvalidUserCredentialException extends BadCredentialsException {

    private String userName;
    private String inputPassword;//remember the password user tried for fraud prevention

    public InvalidUserCredentialException(String userName, String inputPassword) {
        super("wrong password : " + inputPassword);
        this.userName = userName;
        this.inputPassword = inputPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    @Override
    public String toString() {
        return "{" +
                "'userName':'" + userName + '\'' + "," +
                "'inputPassword':'" + inputPassword + '\'' +
                '}';
    }
}
