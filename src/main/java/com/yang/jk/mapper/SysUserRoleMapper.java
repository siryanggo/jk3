package com.yang.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.jk.pojo.po.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色中间表(SysUserRole)表数据库访问层
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
        boolean removeBatch(@Param("id") Integer id);
}

