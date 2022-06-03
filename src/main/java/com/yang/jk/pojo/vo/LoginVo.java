package com.yang.jk.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @auther yhjStart
 * @create 2022-03-29 22:58
 */
@Data
@Api(value = "登录成功的结果")
public class LoginVo {
    //主键
    @ApiModelProperty(value = "用户id")
    private Integer id;
    //昵称
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    //登录的用户名
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户token")
    private String token;
}
