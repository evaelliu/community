package com.evael.community.auth.infrastructure.inmemory;

import com.evael.community.auth.domain.clientdetails.AvailableResources;
import com.evael.community.auth.domain.clientdetails.ClientDetailInfo;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author jiyou
 * @Date  2016/6/23.
 */
public class InMemoryClientDetailServiceImpl implements ClientDetailsService{
    private static ConcurrentHashMap<String, ClientDetailInfo> clientInfoMap = new ConcurrentHashMap();

    static {
        ClientDetailInfo clientShareToMe = new ClientDetailInfo();
        clientShareToMe.setClientId("community110_web");
        clientShareToMe.setClientSecret("{bcrypt}$2a$10$/so85pSMOnUsjw/15i6p6.ZE6dIAHUezOx6EhnT7BDgdsJY2CvqB.");//community110_web_secret
        clientShareToMe.addAuthorizedGrantTypes("password", "refresh_token", "client_credentials");
        clientShareToMe.addAuthorities("ROLE_WEB");
        clientShareToMe.addScopes("SCOPE_ALL");
        clientShareToMe.addResourceIds(
                AvailableResources.API_RESOURCE_ID,
                AvailableResources.USER_RESOURCE_ID,
                AvailableResources.POST_RESOURCE_ID,
                AvailableResources.FILE_RESOURCE_ID,
                AvailableResources.APP_RESOURCE_ID);
        clientShareToMe.setAccessTokenValiditySeconds(2592000);
        clientShareToMe.setRefreshTokenValiditySeconds(2592000);
        clientInfoMap.put(clientShareToMe.getClientId(), clientShareToMe);
    }
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientInfoMap.get(clientId);
    }
}
