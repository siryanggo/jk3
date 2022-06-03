package com.yang.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.jk.Exception.CommonException;
import com.yang.jk.mapper.SysUserMapper;
import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.po.SysUser;
import com.yang.jk.pojo.po.SysUserMap;
import com.yang.jk.pojo.req.SysUserPageReqVo;
import com.yang.jk.pojo.vo.*;
import com.yang.jk.service.RedisService;
import com.yang.jk.service.SysRoleService;
import com.yang.jk.service.SysUserRoleService;
import com.yang.jk.service.SysUserService;
import com.yang.jk.utils.MapStruct;
import com.yang.jk.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 用户(SysUser)表服务实现类
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
 @Autowired
 private SysUserRoleService sysUserRoleService;
 @Autowired
 private SysRoleService sysRoleService;
 @Autowired
 private SysUserMapper sysUserMapper;
 @Autowired
 private RedisService redisService;

 @Override
 public PageVo<SysUserVo> list(SysUserPageReqVo sysUserPageReqVo) {
  String keyword = sysUserPageReqVo.getKeyword();
  LambdaQueryWrapper<SysUser> qw = new LambdaQueryWrapper<>();
  if (keyword!=null && keyword.trim().length()>0) {
   qw.like(SysUser::getUsername,keyword).like(SysUser::getNickname,keyword);
  }
  Page<SysUser> sysUserPage = new Page<>(sysUserPageReqVo.getCurrentPage(),sysUserPageReqVo.getSize());
  baseMapper.selectPage(sysUserPage,qw);
  PageVo<SysUserVo> sysUserPageVo = new PageVo<>();
  sysUserPageVo.setPages(sysUserPage.getPages());
  sysUserPageVo.setCount(sysUserPage.getTotal());
  List<SysUser> records = sysUserPage.getRecords();
  List<SysUserVo> collect = records.stream().map(MapStruct.MAP_STRUCT::PotoVo).collect(Collectors.toList());
  sysUserPageVo.setData(collect);
  return sysUserPageVo;
 }

 @Override
 public boolean addUser(SysUserVo sysUserVo) {
  SysUser sysUser = MapStruct.MAP_STRUCT.VotoPo(sysUserVo);
  sysUser.setPassword(Md5.getEncode(sysUser.getPassword()));
  boolean flag1 = saveOrUpdate(sysUser);
  String ids = sysUserVo.getIds();
  if (ids==null || ids.trim().length()<=0) {
 throw new CommonException("ids不能为空");
  }
  String[] strs = ids.split(",");
  for (String id : strs) {
   SysRole role = sysRoleService.getById(id);
   if (role==null) {
    throw new CommonException("不存在此角色");
   }
  }
  boolean flag0 = sysUserRoleService.addUserAndRoleBatch(sysUser.getId(), strs);

  if (flag0 && flag1) {
   return true;
  }
  return false;

 }

 @Override
 public SysUserVo getByIdUser(Integer id) {
  SysUserMap byIdUser = sysUserMapper.getByIdUser(id);
  SysUserVo sysUserVo = MapStruct.MAP_STRUCT.PotoVo(byIdUser);
  sysUserVo.setRoles(byIdUser.getRoles());
  return sysUserVo;
 }

 @Override
 public boolean editUser(SysUserVo sysUserVo) {
  SysUser sysUser = MapStruct.MAP_STRUCT.VotoPo(sysUserVo);
  String password = sysUserVo.getPassword();
  String newPass = Md5.getEncode(password);
  sysUser.setPassword(newPass);
  boolean flag = updateById(sysUser);
  if (!flag) {
   throw new CommonException("更新用户失败");
  }
  String ids = sysUserVo.getIds();
  String[] strs = ids.split(",");
  boolean flag0 = sysUserRoleService.removeBantch(sysUser.getId());
  if (!flag0) {
   throw new CommonException("传入的角色有误");
  }
  boolean flag1 = sysUserRoleService.addUserAndRoleBatch(sysUser.getId(), strs);


  return flag1;
 }

 @Override
 public LoginVo login(LoginReqVo loginReqVo) {
  SysUser sysUser = sysUserMapper.getByUsername(loginReqVo.getUsername());
  if (sysUser==null) {
   throw new CommonException("用户名不存在");
  }
  if (!sysUser.getPassword().equals(Md5.getEncode(loginReqVo.getPassword()))) {
   throw new CommonException("密码错误");
  }
  if (sysUser.getStatus()==1) {
   throw new CommonException("账号已经锁定");
  }
  sysUser.setLoginTime(new Date());
  baseMapper.updateById(sysUser);
  LoginVo loginVo = MapStruct.MAP_STRUCT.PotoVo1(sysUser);
  String token = UUID.randomUUID().toString();
  System.out.println(token);
  UserPermissVo userPermissVo = sysUserMapper.getUserPermissVo(sysUser.getId());
  redisService.set(token,userPermissVo);
  redisService.permanentSet(sysUser.getId().toString(),token);
  System.out.println(redisService.get(token)+"redis");
  loginVo.setToken(token);
  return loginVo;
 }

}

