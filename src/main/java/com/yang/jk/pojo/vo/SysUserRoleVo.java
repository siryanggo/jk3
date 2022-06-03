package com.yang.jk.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @auther yhjStart
 * @create 2022-03-27 21:55
 */
@Data
public class SysUserRoleVo {
    //角色id
    @ApiModelProperty(value = "This is Role Id,is not null",required = true)
    @NotNull(message = "roleId is not null")
    private Integer roleId;
    //用户id
    @ApiModelProperty(value = "This is User Id,is not null",required = true)
    @NotNull(message = "userId is not null")
    private Integer userId;
}
