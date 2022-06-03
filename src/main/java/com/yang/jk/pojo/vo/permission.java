package com.yang.jk.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther yhjStart
 * @create 2022-04-03 0:24
 */
@Data
public class permission implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "权限")
    private String permission_name;
    private String sr_name;
}
