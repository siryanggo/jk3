package com.yang.jk.controller;




import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.jk.common.R;
import com.yang.jk.controller.base.baseController;
import com.yang.jk.pojo.po.SysResource;
import com.yang.jk.pojo.req.SysResourcePageReqVo;
import com.yang.jk.pojo.req.SysRolePageReqVo;
import com.yang.jk.pojo.vo.PageVo;
import com.yang.jk.pojo.vo.SysResourceTreeVo;
import com.yang.jk.pojo.vo.SysResourceVo;
import com.yang.jk.pojo.vo.SysRoleReqVo;
import com.yang.jk.service.SysResourceService;
import com.yang.jk.utils.Constants;
import com.yang.jk.utils.MapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源(SysResource)表控制层
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
@RestController
@RequestMapping("/sysResource")
@Validated
@Api(tags = "资源")
public class SysResourceController extends baseController<SysResourceVo, SysResource> {

    /**
     * 服务对象
     */
    @Autowired
    private SysResourceService sysResourceService;

    @Override
    protected Function<SysResourceVo, SysResource> func() {
        return MapStruct.MAP_STRUCT::VotoPo;
    }

    @Override
    protected IService<SysResource> getService() {
        return sysResourceService;
    }

    @ApiOperation(value = "资源分页分页")
    @GetMapping("/Pagelist")
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_LIST)
    public R list(SysResourcePageReqVo sysResourcePageReqVo) {
        PageVo<SysResourceVo> list = sysResourceService.list(sysResourcePageReqVo);
        return R.ok().data("data",list);
    }
    @ApiOperation(value = "获取所有的权限数据")
    @GetMapping("/list")
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_LIST)
    public R list() {
        List<SysResourceVo> list =   sysResourceService.getResourceDataList();
        return R.ok().data("data",list);
    }
    @ApiOperation(value = "获取父资源的信息")
    @GetMapping("/ParentList")
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_LIST)
    public R ParentList() {
        List<SysResourceVo> list =  sysResourceService.getParentList();
        return R.ok().data("data",list);
    }
    @ApiOperation(value = "获取资源树状结构")
    @GetMapping("/SysResourceTreeVo")
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_LIST)
    public R getSysResourceTreeVo() {
        List<SysResourceTreeVo> list =  sysResourceService.getSysRegsourceTreeVo();
        return R.ok().data("data",list);
    }

    @ApiOperation(value = "获取角色拥有的资源权限id")
    @GetMapping("/getRoleResourceIds")
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_LIST)
    public R getRoleResourceIds(@NotNull(message = "roleId不能为空") Integer roleId) {
       List<Integer> list =  sysResourceService.getRoleResourceIds(roleId);
       return R.ok().data("data",list);
    }
}

