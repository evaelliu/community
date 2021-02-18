package com.evael.community.account.management.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
@Data
@ApiModel(value = "UpdatePasswordDTO")
public class UpdatePasswordDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4578819000187634606L;

    @ApiModelProperty( required = true, value = "新密码")
    private String newPassword;//新密码
    @ApiModelProperty( required = true, value = "旧密码")
    private String oldPassword;//老密码
}
