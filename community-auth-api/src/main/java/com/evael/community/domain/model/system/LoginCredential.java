package com.evael.community.domain.model.system;

import com.evael.community.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by Guitar on 2015/8/11.
 */
@Data
@NoArgsConstructor
public class LoginCredential implements Serializable, UserDetails {
    private static final long serialVersionUID = -4062518986236230796L;

    private Collection<String> roles = new HashSet<>();
    private Long id;//accountId
    private String nickName;
    private String registerName;
    private String password;
    private Status status = Status.AVAILABLE;
    private String unikey;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return CollectionUtils.isEmpty(this.roles)? Collections.EMPTY_SET:
                this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return registerName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Status.AVAILABLE==this.status;
    }

}
