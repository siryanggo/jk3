package com.yang.jk.controller;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.captcha.utils.CaptchaUtil;
import com.yang.jk.Exception.CommonException;
import com.yang.jk.common.R;
import com.yang.jk.controller.base.baseController;
import com.yang.jk.pojo.po.SysUser;
import com.yang.jk.pojo.req.SysUserPageReqVo;
import com.yang.jk.pojo.vo.LoginReqVo;
import com.yang.jk.pojo.vo.LoginVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysUserVo;
import com.yang.jk.service.RedisService;
import com.yang.jk.service.SysUserService;
import com.yang.jk.utils.Constants;
import com.yang.jk.utils.MapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户(SysUser)表控制层
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
@RestController
@RequestMapping("/sysUser")
@Validated
@Api(tags = "用户")
public class SysUserController extends baseController<SysUserVo, SysUser> {
    @Autowired
    private SysUserService service;
    @Autowired
    private RedisService redisService;
    /**
     * 服务对象
     */

//    @PostMapping
//    public String saveOrUpdate(@Valid SysUserVo sysUserVo) {
//        SysUser sysUser = MapStruct.MAP_STRUCT.VotoPo(sysUserVo);
//    }

    @Override
    protected Function<SysUserVo, SysUser> func() {
        return MapStruct.MAP_STRUCT::VotoPo;
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CaptchaUtil.out(req,resp);
    }

    @Override
    protected IService<SysUser> getService() {
        return service;
    }
    @ApiOperation(value = "分页用户列表")
    @RequiresPermissions(Constants.Permisson.SYS_USER_LIST)
    @GetMapping("/pagelist")
    public R list(SysUserPageReqVo sysUserPageReqVo) {
        PageVo<SysUserVo> list = service.list(sysUserPageReqVo);
        return R.ok().data("data",list);
    }

    /**
     * 添加用户
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    @RequiresPermissions(value = {Constants.Permisson.SYS_USER_ADD,Constants.Permisson.SYS_USER_UPDATE},logical = Logical.AND)
    public R addUser(@Valid SysUserVo sysUserVo) {
            boolean flag = service.addUser(sysUserVo);
            return flag?R.ok():R.error();
    }
    @ApiOperation(value = "根据用户id获取用户的信息和对应的角色信息")
    @GetMapping("/getByIdUser")
    @RequiresPermissions("/SysUser:userAndRole")
    public R getByIdUser(@NotNull @RequestParam Integer id) {
         SysUserVo sysUserVo =    service.getByIdUser(id);
         return R.ok().data("data",sysUserVo);
    }
    @ApiOperation(value = "编辑用户信息")
    @PostMapping("/editUser")
    public R editUser(@Valid SysUserVo sysUserVo) {
      boolean  flag =  service.editUser(sysUserVo);
      return flag?R.ok():R.error();
    }
    @ApiOperation(value = "登录")
    @PostMapping("/Login")
    public R Login(@Valid LoginReqVo loginReqVo,HttpServletRequest req) {
        if (!CaptchaUtil.ver(loginReqVo.getCode(),req)) {
           throw new CommonException("验证码错误");
        }
          LoginVo loginVo =  service.login(loginReqVo);
           return R.ok().data("data",loginVo);
    }
    @ApiOperation(value = "退出登录")
    @GetMapping("/Logout")
    public R Logout(@RequestHeader("token") String token) {
        Boolean flag = redisService.delete(token);
        return flag?R.ok().message("退出成功"):R.error();
    }

    @Override
    @RequiresPermissions(Constants.Permisson.SYS_USER_REMOVE)
    public R removeById(String Ids) {
        return super.removeById(Ids);
    }

    /**
     * 强制下线
     * @return
     */
    @ApiOperation(value = "强制下线")
    @GetMapping("/RemoveToken")
    public R forciblyRemoveToken(@NotNull String id) {
        String  token = (String) redisService.get(id);
        Boolean f1 = redisService.delete(token);
        Boolean f2 = redisService.delete(id);
        if (f1 && f2) {
            return R.ok();
        }
        return R.error();
    }
    //    public R edit()
}

