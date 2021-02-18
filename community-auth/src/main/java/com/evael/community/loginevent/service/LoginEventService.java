package com.evael.community.loginevent.service;

import com.evael.community.common.util.IPUtils;
import com.evael.community.loginevent.domain.LoginEvent;
import com.evael.community.loginevent.domain.LoginEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LoginEventService {

    private final LoginEventRepository loginEventRepository;
    private final HttpServletRequest request;

    @Async
    public void createSuccessLoginEvent(String username){
        LoginEvent loginEvent = generateLoginEvent(username);
        loginEventRepository.save(loginEvent);
    }


    @Async
    public void createFailedLoginEvent(String username,String errMsg){
        LoginEvent loginEvent = generateLoginEvent(username);
        loginEvent.setSuccess(false);
        loginEvent.setErrMsg(errMsg);
        loginEventRepository.save(loginEvent);
    }


    private LoginEvent generateLoginEvent(String username) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setUsername(username);
        String ipAddr = IPUtils.getIpAddr(request);
        if (!StringUtils.isEmpty(ipAddr)) {
            loginEvent.setIp(ipAddr);
            loginEvent.setAddress(IPUtils.getAddressByIp(ipAddr));
        }
        return loginEvent;
    }
}
