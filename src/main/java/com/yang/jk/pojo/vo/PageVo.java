package com.yang.jk.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-03-28 16:17
 */
@Data
@ApiModel("分页结果")
public class PageVo<T> {
    @ApiModelProperty(value = "总数据数量")
    private long count;
    @ApiModelProperty(value = "总页数")
    private long pages;
    @ApiModelProperty(value = "数据")
    private List<T> data;
}
