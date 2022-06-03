package com.yang.jk.pojo.vo;

import com.yang.jk.pojo.po.SysResource;
import com.yang.jk.pojo.po.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-04-02 23:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "角色名")
    private List<Role> roles;

}
