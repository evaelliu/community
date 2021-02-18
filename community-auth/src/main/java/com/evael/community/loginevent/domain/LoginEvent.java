package com.evael.community.loginevent.domain;

import com.evael.community.common.util.IdWorker;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "login_event")
public class LoginEvent {
    @Id
    private long id = IdWorker.randomId();
    private String username;
    private Date date = new Date();
    private boolean success = true;
    private String ip;
    private String address;
    private String errMsg;
}
