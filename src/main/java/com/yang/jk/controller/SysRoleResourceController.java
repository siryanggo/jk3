package com.yang.jk.controller;




import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import com.yang.jk.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色资源中间表(SysRoleResource)表控制层
 *
 * @author yhj
 * @since 2022-03-27 20:48:28
 */
@RestController
@RequestMapping("/sysRoleResource")
public class SysRoleResourceController {
    /**
     * 服务对象
     */
    @Autowired
    private SysRoleResourceService sysRoleResourceService;

}

