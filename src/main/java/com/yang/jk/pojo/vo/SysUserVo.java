package com.yang.jk.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yang.jk.pojo.po.SysRole;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-03-27 22:03
 */
@Data
public class SysUserVo {
    //主键
    @ApiModelProperty(value = "ID is No necessary,But if it exist,is Update operate. no it exist, is edit operate")
    private Integer id;
    //昵称
    @ApiModelProperty(value = "nickname is required",required = true)
    @NotBlank(message = "nickname not null")
    private String nickname;
    //登录的用户名
    @ApiModelProperty(value = "username is required",required = true)
    @NotBlank(message = "username not null")
    private String username;
    //登录用的密码
    @ApiModelProperty(value = "password is required",required = true)
    @NotBlank(message = "password not null")
    private String password;
    //账号的状态,0是正常,1是锁定
    @Range(min = 0,max = 1,message = "status only 1 or 0")
    @ApiModelProperty(value = "password is required,0 is normal,1 is locking,default value is 0",required = true)
    private Short status;

    //最后一次登录的时间
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "所拥有的角色iD",required = true)
    @NotBlank(message = "ids不能为空")
    private String Ids;
    @ApiModelProperty(hidden = true)
    private List<SysRole> roles;
}
