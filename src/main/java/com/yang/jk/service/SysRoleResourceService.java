package com.yang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.pojo.po.SysRoleResource;

/**
 * 角色资源中间表(SysRoleResource)表服务接口
 *
 * @author yhj
 * @since 2022-03-27 20:48:28
 */
public interface SysRoleResourceService extends IService<SysRoleResource> {
    public boolean addRoleAndResBatch(Integer roleId, String[] resourceIds);
    public void removeByRoleId(Integer roleId);
}

