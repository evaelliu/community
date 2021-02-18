package com.evael.community.account.management.facade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @Author jiyou
 * @Date  2016/3/8.
 */
@Data
@ApiModel(value = "AccountRegisterDTO")
public class AccountRegisterDTO extends AbstractScalableDTO {
    @ApiModelProperty( required = false, value = "注册名称")
    //register keys
    private String registerName;//注册名
    @ApiModelProperty( required = false, value = "昵称")
    private String nickName;//昵称别名
    //private String avatarUrl;//头像镜像地址
    @ApiModelProperty( required = false, value = "密码")
    private String password;//密码
}
