package com.yang.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.jk.Exception.CommonException;
import com.yang.jk.mapper.SysUserRoleMapper;
import com.yang.jk.pojo.po.SysRoleResource;
import com.yang.jk.pojo.po.SysUserRole;
import com.yang.jk.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * 用户角色中间表(SysUserRole)表服务实现类
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
@Service("sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public boolean addUserAndRoleBatch(Integer userId, String[] rolds) {
        System.out.println(userId);
        ArrayList<SysUserRole> sysUserRoles = new ArrayList<>();
        for (String rold : rolds) {
            Integer rold_I = new Integer(rold);
            SysUserRole sysUserRole = new SysUserRole(rold_I,userId);
            sysUserRoles.add(sysUserRole);
        }
        boolean flag = saveBatch(sysUserRoles);
        if (!flag) {
            throw new CommonException("插入用户角色失败");
        }
        return flag;
    }

    @Override
    public boolean removeBantch(Integer id) {
        LambdaQueryWrapper<SysUserRole> qw = new LambdaQueryWrapper<>();
        qw.eq(SysUserRole::getUserId,id);
        boolean flag = remove(qw);
        return flag;

//        LambdaQueryWrapper<SysRoleResource> qw = new LambdaQueryWrapper<>();
//        qw.eq(SysRoleResource::getRoleId,roleId);
//        remove(qw);
    }
}

