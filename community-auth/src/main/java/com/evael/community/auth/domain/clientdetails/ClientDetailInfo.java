package com.evael.community.auth.domain.clientdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @Author jiyou
 * @Date  2016/6/17.
 */
@Entity
@Table(name = "client_configure")
public class ClientDetailInfo implements Serializable,ClientDetails {
    @Id
    String id;
    String clientId;
    String clientSecret;
    @ElementCollection
    @CollectionTable(name = "resourceId")
    Set<String> resourceIds = new HashSet<>();
    Boolean isScoped = false;
    Boolean isSecretRequired = false;
    @ElementCollection
    @CollectionTable(name = "scope")
    Set<String> scope = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "authorizedGrantType")
    Set<String> authorizedGrantTypes = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "registeredRedirectUri")
    Set<String> registeredRedirectUri = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "authorities")
    Collection<String> authorities = new ArrayList<>();
    Integer accessTokenValiditySeconds = 0;
    Integer refreshTokenValiditySeconds = 0;

    public ClientDetailInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void addResourceIds(String... resourceIds) {
        for (String resourceId : resourceIds) {
            this.resourceIds.add(resourceId);
        }
    }

    public Boolean getIsScoped() {
        return isScoped;
    }

    public void setIsScoped(Boolean isScoped) {
        this.isScoped = isScoped;
    }

    public Boolean getIsSecretRequired() {
        return isSecretRequired;
    }

    public void setIsSecretRequired(Boolean isSecretRequired) {
        this.isSecretRequired = isSecretRequired;
    }

    public void addScopes(String... scopes) {
        for (String scope : scopes){
            this.scope.add(scope);
        }
    }

    public void addAuthorizedGrantTypes(String... authorizedGrantTypes) {
        for (String grant : authorizedGrantTypes) {
            this.authorizedGrantTypes.add(grant);
        }
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public void addAuthorities(String... authorities) {
        for (String authority : authorities) {
            this.authorities.add(authority);
        }
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return this.isSecretRequired;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.isScoped;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> result = new ArrayList<>();
        for (String authority:this.authorities){
            result.add(new SimpleGrantedAuthority(authority));
        }
        return result;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;//TODO
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return "ClientDetailInfo{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", resourceIds=" + resourceIds +
                ", isScoped=" + isScoped +
                ", isSecretRequired=" + isSecretRequired +
                ", scope=" + scope +
                ", authorizedGrantTypes=" + authorizedGrantTypes +
                ", registeredRedirectUri=" + registeredRedirectUri +
                ", authorities=" + authorities +
                ", accessTokenValiditySeconds=" + accessTokenValiditySeconds +
                ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds +
                '}';
    }
}
