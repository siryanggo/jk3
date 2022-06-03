package com.yang.jk.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-04-01 21:00
 */
@Data
public class SysResourceTreeVo {
    @ApiModelProperty(value = "资源id")
    private Integer id;
    @ApiModelProperty(value = "资源名称")
    private String title;
    @ApiModelProperty(value = "是否展开,大于零就是展开")
    private Integer spread;
    @ApiModelProperty(value = "子资源")
    private List<SysResourceTreeVo> children;
}
