package com.yang.jk.controller;




import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import com.yang.jk.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色中间表(SysUserRole)表控制层
 *
 * @author yhj
 * @since 2022-03-27 20:48:29
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserRoleService sysUserRoleService;

}

