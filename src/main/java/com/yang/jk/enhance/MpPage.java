package com.yang.jk.enhance;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.jk.pojo.req.PageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.utils.ConvertUtil;

import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-03-28 16:28
 */
public class MpPage<T> extends Page<T> {
    private PageReqVo pageReqVo;
    public MpPage(PageReqVo pageReqVo) {
        super(pageReqVo.getCurrentPage(),pageReqVo.getSize());
        this.pageReqVo = pageReqVo;
    }
    public<N> PageVo<N> CommonToPageVo(List<N> list) {
        PageVo<N> objectPageVo = new PageVo<>();
        objectPageVo.setPages(getPages());
        objectPageVo.setCount(getTotal());
        objectPageVo.setData(list);
        return objectPageVo;
    }
    public<R> PageVo<R> buildVo(SFunction<T,R> func) {
        return CommonToPageVo(ConvertUtil.map(getRecords(),func));
    }



}
