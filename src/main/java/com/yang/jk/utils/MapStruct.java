package com.yang.jk.utils;

import com.yang.jk.pojo.po.SysResource;
import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.po.SysUser;
import com.yang.jk.pojo.po.SysUserMap;
import com.yang.jk.pojo.vo.LoginVo;
import com.yang.jk.pojo.vo.SysResourceVo;
import com.yang.jk.pojo.vo.SysRoleReqVo;
import com.yang.jk.pojo.vo.SysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @auther yhjStart
 * @create 2022-03-27 21:43
 */
@Mapper(uses ={
        DateForMatter.class
})
public interface MapStruct {
    MapStruct MAP_STRUCT = Mappers.getMapper(MapStruct.class);
    SysUser VotoPo(SysUserVo sysUserVo);
//    @Mapping(source = "loginTime",
//            target = "loginTime",
//            qualifiedBy = DateForMatter.Date2Millis.class)
    SysUserVo PotoVo(SysUser sysUser);

    SysUserVo PotoVo(SysUserMap sysUserMap);

    SysRole VotoPo(SysRoleReqVo sysRoleReqVo);

    SysRoleReqVo PotoVo(SysRole sysRole);

    SysResource VotoPo(SysResourceVo sysResourceVo);

    SysResourceVo PotoVo(SysResource sysResource);

    LoginVo PotoVo1(SysUser sysUser);



}
