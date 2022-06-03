package com.yang.jk.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-04-03 0:23
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "角色名")
    private String role_name;
    @ApiModelProperty(value = "权限")
    private List<permission> data;
}
