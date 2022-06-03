package com.yang.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.jk.Exception.CommonException;
import com.yang.jk.enhance.MpPage;
import com.yang.jk.enhance.MyLambdaQw;
import com.yang.jk.mapper.SysResourceMapper;
import com.yang.jk.mapper.SysRoleResourceMapper;
import com.yang.jk.pojo.po.SysResource;
import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.po.SysRoleResource;
import com.yang.jk.pojo.req.SysResourcePageReqVo;
import com.yang.jk.pojo.req.SysRolePageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysResourceTreeVo;
import com.yang.jk.pojo.vo.SysResourceVo;
import com.yang.jk.pojo.vo.SysRoleReqVo;
import com.yang.jk.service.SysResourceService;
import com.yang.jk.utils.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源(SysResource)表服务实现类
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
@Service("sysResourceService")
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
 @Autowired
 private SysRoleResourceMapper sysRoleResourceMapper;
 @Autowired
 private SysResourceMapper sysResourceMapper;
 public PageVo<SysResourceVo> list(SysResourcePageReqVo sysResourcePageReqVo) {
  MpPage<SysResource> page = new MpPage<SysResource>(sysResourcePageReqVo);
  MyLambdaQw<SysResource> qw = new MyLambdaQw<>();
  qw.like(sysResourcePageReqVo.getKeyword(),SysResource::getName);
  return baseMapper.selectPage(page,qw).buildVo(MapStruct.MAP_STRUCT::PotoVo);

 }

 @Override
 public List<SysResourceVo> getResourceDataList() {
  List<SysResourceVo> list = list().stream().map(MapStruct.MAP_STRUCT::PotoVo).collect(Collectors.toList());
  List<SysResourceVo> newList = new ArrayList<>();
  for (SysResourceVo sysResourceVo : list) {
      if (sysResourceVo.getParentId().equals(0)) {
       sysResourceVo.setData(getList(sysResourceVo.getId(),list));
       newList.add(sysResourceVo);
      }
  }
  return newList;
 }



 public List<SysResourceVo> getList(Integer id,List<SysResourceVo> list) {
  ArrayList<SysResourceVo> sysResourceVos = new ArrayList<>();
  for (SysResourceVo sysResourceVo : list) {
     if (sysResourceVo.getParentId().equals(id)) {
      sysResourceVo.setData(getList(sysResourceVo.getId(),list));
      sysResourceVos.add(sysResourceVo);
     }
  }
  return sysResourceVos;
 }

 @Override
 public List<SysResourceVo> getParentList() {
  LambdaQueryWrapper<SysResource> qw = new LambdaQueryWrapper<>();
  qw.ne(SysResource::getType,2);
  List<SysResourceVo> list = baseMapper.selectList(qw).stream().map(MapStruct.MAP_STRUCT::PotoVo).collect(Collectors.toList());
  return list;
 }

 @Override
 public List<SysResourceTreeVo> getSysRegsourceTreeVo() {
  List<SysResourceTreeVo>  list =  sysResourceMapper.getSysRegsourceTreeVo();
  return list;
 }

 /**
  * 获取角色拥有的权限id
  * @param roleId
  * @return
  */
 @Override
 public List<Integer> getRoleResourceIds(Integer roleId) {
  if (roleId<=0) {
   throw new CommonException("角色id错误");
  }
  LambdaQueryWrapper<SysRoleResource> qw = new LambdaQueryWrapper<>();
  qw.select(SysRoleResource::getResourceId);
  qw.eq(SysRoleResource::getRoleId,roleId);
  List<Integer> list = sysRoleResourceMapper.selectList(qw).stream().map(e -> e.getResourceId()).collect(Collectors.toList());

  return list;
 }
}

