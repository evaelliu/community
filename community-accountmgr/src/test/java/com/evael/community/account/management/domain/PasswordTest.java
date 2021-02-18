package com.evael.community.account.management.domain;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {

    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;

    public static void main(String[] args) {
        String password = "community110_web_secret";
        String a;
        System.out.println(a = passwordEncoder.encode(password));
        System.out.println(passwordEncoder.encode(password));
        System.out.println(passwordEncoder.encode(password));
        System.out.println(passwordEncoder.encode(password));

        boolean matches = passwordEncoder.matches(password, a);
        System.out.println(matches);
    }
}