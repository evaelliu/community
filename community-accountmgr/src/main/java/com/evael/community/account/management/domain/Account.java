package com.evael.community.account.management.domain;

import com.evael.community.common.util.IdWorker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account",indexes = {@Index(columnList = "registerName")})
public class Account implements Serializable {

    @Id
    @Getter @Setter
    protected Long id;
    private String nickName;//昵称别名
    private String registerName;                //注册用户名,系统生成
    private String password;                    //密码
    private AccountStatus status;               //状态,可用或者禁用

    //private String avatarUrl;//头像镜像地址  ／＊作为静态文件存储＊／
    private String roles;

    public void generateNextId() {
        if (this.id == null) {
            this.id = generateId();
        }
    }
    public long generateId() {
        return IdWorker.randomId();
    }
}
