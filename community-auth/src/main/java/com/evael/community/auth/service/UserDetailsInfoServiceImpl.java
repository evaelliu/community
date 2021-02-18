package com.evael.community.auth.service;

import com.evael.community.auth.domain.accountDetails.Account;
import com.evael.community.auth.domain.accountDetails.AccountRepository;
import com.evael.community.domain.model.system.LoginCredential;
import com.evael.community.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Objects;

/**
 * Created by Kai.Zhang on 3/5/15.
 */
@Service("userDetailInMongo")
public class UserDetailsInfoServiceImpl implements UserDetailsService{
    final static String LOGIN_USERNAME_SEPARATOR = "@@@";

    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account accountByKey;
        String unikey  = null;
        if(login.indexOf(LOGIN_USERNAME_SEPARATOR)>0) {
            String[] split = login.split(LOGIN_USERNAME_SEPARATOR);
            login = split[0];
            unikey = split[1];
        }

        accountByKey = getAccountByKey(login);
        if(accountByKey==null) {
            if("admin".equals(login)) {
                return getDefaultAdminLoginCredential();
            }
            throw new UsernameNotFoundException(login);
        }
        if(!StringUtils.isEmpty(unikey)&&StringUtils.isEmpty(accountByKey.getUnikey())){
            accountByKey.setUnikey(unikey);
            accountRepository.save(accountByKey);
        }

        if(!StringUtils.isEmpty(unikey)&&!StringUtils.isEmpty(accountByKey.getUnikey())&& !Objects.equals(unikey,accountByKey.getUnikey()))
            throw new BadCredentialsException("unikey:" + unikey + " not match");

        return convertAccountInfoToLoginCredential(accountByKey);
    }

    public Account getAccountByKey(String key) {
        return accountRepository.findAccountByRegisterName(key);
    }

    public LoginCredential convertAccountInfoToLoginCredential(Account accountInfo){
        LoginCredential loginCredential = new LoginCredential();
        loginCredential.setId(accountInfo.getId());
        loginCredential.setRegisterName(accountInfo.getRegisterName());
        loginCredential.setPassword(accountInfo.getPassword());
        if (accountInfo.getStatus()!=null) {
            loginCredential.setStatus(accountInfo.getStatus());
        }
        loginCredential.setNickName(accountInfo.getNickName());
        loginCredential.setUnikey(accountInfo.getUnikey());
        //loginCredential.setAvatarUrl(accountInfo.getAvatarUrl());
        if(!CollectionUtils.isEmpty(accountInfo.getRoles())) {
            loginCredential.setRoles(accountInfo.getRoles());
        }
        return loginCredential;
    }

    public LoginCredential getDefaultAdminLoginCredential(){
        LoginCredential loginCredential = new LoginCredential();
        loginCredential.setId(1L);
        loginCredential.setRegisterName("admin");
        loginCredential.setPassword("{bcrypt}$2a$10$nXF.STtPBLSTpIps0Ry/AuCEpD.pJ9zbvnAvx8qDVKPRr9h9VXVim"); // bcrypt encoded 'community123'
        loginCredential.setStatus(Status.AVAILABLE);
        loginCredential.setNickName("admin");
        loginCredential.setRoles(Collections.singleton("ADMIN"));
        return loginCredential;
    }
}
