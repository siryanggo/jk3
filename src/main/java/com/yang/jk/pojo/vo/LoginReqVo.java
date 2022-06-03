package com.yang.jk.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @auther yhjStart
 * @create 2022-03-29 21:23
 */
@Data
public class LoginReqVo {
    //登录的用户名
    @ApiModelProperty(value = "username is required",required = true)
    @NotBlank(message = "username not null")
    private String username;
    //登录用的密码
    @ApiModelProperty(value = "password is required",required = true)
    @NotBlank(message = "password not null")
    private String password;
    @ApiModelProperty(value = "验证码",required = true)
    @NotBlank(message = "验证码不能为空")
    private String code;
}
