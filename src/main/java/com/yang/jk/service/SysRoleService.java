package com.yang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.req.SysRolePageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysRoleReqVo;

/**
 * 角色(SysRole)表服务接口
 *
 * @author yhj
 * @since 2022-03-27 20:48:28
 */
public interface SysRoleService extends IService<SysRole> {
    public PageVo<SysRoleReqVo> list(SysRolePageReqVo sysRolePageReqVo);

    boolean addRoleAndRes(SysRoleReqVo sysRoleReqVo);
}

