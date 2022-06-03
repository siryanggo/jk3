package com.yang.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.jk.Exception.CommonException;
import com.yang.jk.enhance.MpPage;
import com.yang.jk.enhance.MyLambdaQw;
import com.yang.jk.mapper.SysRoleMapper;
import com.yang.jk.mapper.SysUserRoleMapper;
import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.po.SysRoleResource;
import com.yang.jk.pojo.po.SysUserRole;
import com.yang.jk.pojo.req.SysRolePageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysRoleReqVo;
import com.yang.jk.service.RedisService;
import com.yang.jk.service.SysRoleResourceService;
import com.yang.jk.service.SysRoleService;
import com.yang.jk.utils.ConvertUtil;
import com.yang.jk.utils.MapStruct;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色(SysRole)表服务实现类
 *
 * @author yhj
 * @since 2022-03-27 20:48:28
 */
@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
 @Autowired
 private SysRoleResourceService sysRoleResourceService;
 @Autowired
 private SysUserRoleMapper sysUserRoleMapper;
 @Autowired
 private RedisService redisService;
 public PageVo<SysRoleReqVo> list(SysRolePageReqVo sysRolePageReqVo) {
   MpPage<SysRole> page = new MpPage<SysRole>(sysRolePageReqVo);
  MyLambdaQw<SysRole> qw = new MyLambdaQw<>();
  qw.like(sysRolePageReqVo.getKeyword(),SysRole::getName);
  return baseMapper.selectPage(page,qw).buildVo(MapStruct.MAP_STRUCT::PotoVo);

 }

 @Override
 public boolean addRoleAndRes(SysRoleReqVo sysRoleReqVo) {
  SysRole sysRole = MapStruct.MAP_STRUCT.VotoPo(sysRoleReqVo);
  boolean flag = saveOrUpdate(sysRole);
  if (!flag) {
      throw new CommonException("添加或更新失败");
  }
  if (sysRoleReqVo.getId()!=null &&sysRoleReqVo.getId()>0 ) {
   LambdaQueryWrapper<SysUserRole> qw = new LambdaQueryWrapper<>();

   qw.eq(SysUserRole::getRoleId,sysRoleReqVo.getId());
   List<String> ids = sysUserRoleMapper.selectList(qw).stream().map(e -> e.getUserId().toString()).collect(Collectors.toList());
   List<String> tokens = redisService.batchGet(ids).stream().map(e -> (String) e).collect(Collectors.toList());
   redisService.batchDel(tokens);
   redisService.batchDel(ids);
   sysRoleResourceService.removeByRoleId(sysRole.getId());
  }
  String resourceIds = sysRoleReqVo.getResourceIds();
  if (Strings.isEmpty(resourceIds)) {
   return true;
  }
  String[] reIds = resourceIds.split(",");
  boolean flag1 = sysRoleResourceService.addRoleAndResBatch(sysRole.getId(), reIds);

  return flag1;
 }
}

