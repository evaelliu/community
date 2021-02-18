package com.evael.community.auth.domain.authentication.provider;

import com.evael.community.loginevent.service.LoginEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
/**
 * Created by Guitar on 2015/12/16.
 */
@Slf4j
public class GeneralAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    LoginEventService loginEventService;

    public GeneralAuthenticationProvider() {
        this.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String presentedPassword = authentication.getCredentials().toString();
        if(!this.passwordEncoder.matches(presentedPassword,userDetails.getPassword())) {
            this.logger.debug("Authentication failed: password does not match stored value");
            loginEventService.createFailedLoginEvent(userDetails.getUsername(), "wrong password");
            throw new BadCredentialsException(authentication.getName());
        }
        loginEventService.createSuccessLoginEvent(userDetails.getUsername());
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        logger.info("[Login] login as general user");
        try {
            return this.getUserDetailsService().loadUserByUsername(username);
        }catch (Exception e){
            loginEventService.createFailedLoginEvent(username.split("@@@")[0],e.getClass().getSimpleName()+':'+e.getMessage());
            throw e;
        }
    }

    public void setPasswordEncoder(Object passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        if(passwordEncoder instanceof PasswordEncoder) {
            this.setPasswordEncoder((PasswordEncoder)passwordEncoder);
        } else {
            throw new IllegalArgumentException("passwordEncoder must be a PasswordEncoder instance");
        }
    }

    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }
}
