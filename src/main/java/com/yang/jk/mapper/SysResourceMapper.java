package com.yang.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.jk.pojo.po.SysResource;
import com.yang.jk.pojo.vo.SysResourceTreeVo;

import java.util.List;

/**
 * 资源(SysResource)表数据库访问层
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResourceTreeVo> getSysRegsourceTreeVo();
}

