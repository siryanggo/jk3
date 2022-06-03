package com.yang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.pojo.po.SysUser;
import com.yang.jk.pojo.req.SysUserPageReqVo;
import com.yang.jk.pojo.vo.LoginReqVo;
import com.yang.jk.pojo.vo.LoginVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysUserVo;

/**
 * 用户(SysUser)表服务接口
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
public interface SysUserService extends IService<SysUser> {
    public PageVo<SysUserVo> list(SysUserPageReqVo sysUserPageReqVo);

    boolean addUser(SysUserVo sysUserVo);

    SysUserVo getByIdUser(Integer id);

    boolean editUser(SysUserVo sysUserVo);

    LoginVo login(LoginReqVo loginReqVo);
}

