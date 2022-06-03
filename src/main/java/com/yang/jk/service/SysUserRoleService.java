package com.yang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.pojo.po.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色中间表(SysUserRole)表服务接口
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
public interface SysUserRoleService extends IService<SysUserRole> {
        public boolean addUserAndRoleBatch(Integer userId,String [] rolds);
        boolean removeBantch(Integer id);
}

