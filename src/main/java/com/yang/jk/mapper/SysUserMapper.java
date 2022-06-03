package com.yang.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.jk.pojo.po.SysUser;
import com.yang.jk.pojo.po.SysUserMap;
import com.yang.jk.pojo.vo.UserPermissVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户(SysUser)表数据库访问层
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
        SysUserMap getByIdUser(Integer id);
        SysUser getByUsername(@Param("name") String name);
        UserPermissVo getUserPermissVo(@Param("id") Integer userId);
}

