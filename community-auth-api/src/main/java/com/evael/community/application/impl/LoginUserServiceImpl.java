package com.evael.community.application.impl;

import com.alibaba.fastjson.JSON;
import com.evael.community.application.LoginUserService;
import com.evael.community.domain.model.system.LoginCredential;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @Author jiyou
 * @Date  2016/2/25.
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {
    private final static Logger logger = Logger.getLogger(LoginUserServiceImpl.class);
    @Override
    public LoginCredential getLoginUser() {
        final OAuth2Authentication requestingUser = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (requestingUser==null)
            return null;
        printResourceIds(requestingUser);
        final Object principal = requestingUser.getPrincipal();

        if (principal instanceof LoginCredential) {
            LoginCredential loginCredential = (LoginCredential) principal;
            return loginCredential;
        }else if(principal instanceof String){
            LoginCredential parse = JSON.parseObject((String) principal,LoginCredential.class);
            return parse;
        }
            return null;
    }

    private void printResourceIds(OAuth2Authentication requestingUser) {
        logger.info("requestingUser.getOAuth2Request().getResourceIds() = " + requestingUser.getOAuth2Request().getResourceIds());
        if (!CollectionUtils.isEmpty(requestingUser.getOAuth2Request().getResourceIds())){
            for (String resourceId : requestingUser.getOAuth2Request().getResourceIds()){
                logger.info("resource id = " + resourceId);
            }
        }
    }

    @Override
    public String getClientId() {
        final OAuth2Authentication requestingUser = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (requestingUser==null)
            return null;
        printResourceIds(requestingUser);
        final OAuth2Request request = requestingUser.getOAuth2Request();
        return request.getClientId();
    }

    @Override
    public String getAccessTokenValue() {
        final OAuth2Authentication requestingUser = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (requestingUser!=null && requestingUser.getDetails()!=null){
            String authToken = ((OAuth2AuthenticationDetails)requestingUser.getDetails()).getTokenValue();
            if (authToken!=null)
                return authToken;
        }
        return null;
    }
}
