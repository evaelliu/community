package com.evael.community.auth.domain.accountDetails;

import com.evael.community.enums.Status;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @Author jiyou
 * @Date  2018/3/2.
 */
@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    private Long id;
    private String nickName;
    private String registerName;
    private String password;
    private Status status;
    private String unikey;

    private String roles;

    public Collection<String> getRoles(){
        return StringUtils.isEmpty(roles)?Collections.emptySet(): Arrays.asList(roles.split(","));
    }

}
