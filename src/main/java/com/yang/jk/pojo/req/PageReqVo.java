package com.yang.jk.pojo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther yhjStart
 * @create 2022-03-28 16:06
 */
@Data
public class PageReqVo {
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private long currentPage;
    /**
     * 当前页面数据量
     */
    @ApiModelProperty(value = "每页数据量")
    private long size;

    public long getCurrentPage() {
        return currentPage<1?1:currentPage;
    }

    public long getSize() {
        return size<1?6:size;
    }
}
