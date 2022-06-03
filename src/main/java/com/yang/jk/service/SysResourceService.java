package com.yang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.pojo.po.SysResource;
import com.yang.jk.pojo.req.SysResourcePageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysResourceTreeVo;
import com.yang.jk.pojo.vo.SysResourceVo;

import java.util.List;

/**
 * 资源(SysResource)表服务接口
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
public interface SysResourceService extends IService<SysResource> {
    public PageVo<SysResourceVo> list(SysResourcePageReqVo sysResourcePageReqVo);

    List<SysResourceVo> getResourceDataList();

    List<SysResourceVo> getParentList();

    List<SysResourceTreeVo> getSysRegsourceTreeVo();

    List<Integer> getRoleResourceIds(Integer roleId);
}

