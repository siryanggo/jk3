package com.yang.jk.controller;




import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.common.R;
import com.yang.jk.controller.base.baseController;
import com.yang.jk.pojo.po.SysRole;
import com.yang.jk.pojo.req.SysRolePageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysRoleReqVo;
import com.yang.jk.service.SysRoleService;
import com.yang.jk.utils.Constants;
import com.yang.jk.utils.MapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.constructor.BaseConstructor;

/**
 * 角色(SysRole)表控制层
 *
 * @author yhj
 * @since 2022-03-27 20:48:28
 */
@RestController
@RequestMapping("/sysRole")
@Validated
@RequiresRoles(value = "中组长")
@Api(tags = "角色")
public class SysRoleController extends baseController<SysRoleReqVo, SysRole> {
    /**
     * 服务对象
     */
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    protected Function<SysRoleReqVo, SysRole> func() {
        return MapStruct.MAP_STRUCT::VotoPo;
    }
    @ApiOperation(value = "角色分页")
    @GetMapping("/Pagelist")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public R list(SysRolePageReqVo sysRolePageReqVo) {
        PageVo<SysRoleReqVo> list = sysRoleService.list(sysRolePageReqVo);
        return R.ok().data("data",list);
    }

    @ApiOperation(value = "添加角色以及其拥有的权限")
    @PostMapping("/addRoleAndRes")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_ADD)
    public R addRoleAndRes(@Valid SysRoleReqVo sysRoleReqVo) {
        boolean flag = sysRoleService.addRoleAndRes(sysRoleReqVo);

        return flag?R.ok():R.ok();
    }

    @Override
    protected IService<SysRole> getService() {
        return sysRoleService;
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/list")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public R list() {
        List<SysRole> list = sysRoleService.list();
        return R.ok().data("list",list);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_REMOVE)
    public R removeById(String Ids) {
        return super.removeById(Ids);
    }

    @ApiOperation(value = "测试权限")
    @GetMapping("/test")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_REMOVE)
    public R test() {
        System.out.println(111);
       return R.ok();
    }
    @ApiOperation(value = "测试权限2")
    @GetMapping("/test2")
    @RequiresPermissions(Constants.Permisson.SYS_USER_LIST)
    public R test2() {
        System.out.println(111);
        return R.ok();
    }

}

