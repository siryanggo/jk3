package com.yang.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.jk.Exception.CommonException;
import com.yang.jk.mapper.SysRoleResourceMapper;
import com.yang.jk.pojo.po.SysRoleResource;
import com.yang.jk.pojo.po.SysUserRole;
import com.yang.jk.service.SysRoleResourceService;
import com.yang.jk.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * 角色资源中间表(SysRoleResource)表服务实现类
 *
 * @author yhj
 * @since 2022-03-27 20:48:28
 */
@Service("sysRoleResourceService")
@Transactional
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {
 @Autowired
 private SysRoleResourceMapper SysRoleResourceMapper;
 public boolean addRoleAndResBatch(Integer roleId, String[] resourceIds) {
  System.out.println(roleId);
  ArrayList<SysRoleResource> sysRoleResources = new ArrayList<>();
  for (String resourId : resourceIds) {
   Integer resourId_I = new Integer(resourId);
   SysRoleResource sys = new SysRoleResource(roleId,resourId_I);
   sysRoleResources.add(sys);
  }
  boolean flag = saveBatch(sysRoleResources);
  if (!flag) {
   throw new CommonException("插入用户角色权限失败");
  }
  return flag;
 }

 @Override
 public void removeByRoleId(Integer roleId) {
  LambdaQueryWrapper<SysRoleResource> qw = new LambdaQueryWrapper<>();
  qw.eq(SysRoleResource::getRoleId,roleId);
  remove(qw);
 }
}

