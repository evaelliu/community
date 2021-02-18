package com.evael.community.application;

import com.evael.community.domain.model.system.LoginCredential;

/**
 * Created by Kai.Zhang on 3/16/15.
 */
public interface LoginUserService {

    LoginCredential getLoginUser();

    String getClientId();

    String getAccessTokenValue();
}
