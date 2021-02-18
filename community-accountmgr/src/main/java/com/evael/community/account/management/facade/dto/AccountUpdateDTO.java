package com.evael.community.account.management.facade.dto;

import lombok.*;

/**
 * @Author jiyou
 * @Date  2018/1/19.
 */
@Data
public class AccountUpdateDTO {
    private Long accountId;
    private String nickName;//昵称别名
    //private String avatarUrl;//头像镜像地址  ／＊作为静态文件存储＊／
    private String status;
    private String password;
}
