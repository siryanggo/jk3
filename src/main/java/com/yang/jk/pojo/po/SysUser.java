package com.yang.jk.pojo.po;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class SysUser  {
    //主键
    private Integer id;
    //昵称
    private String nickname;
    //登录的用户名
    private String username;
    //登录用的密码
    private String password;
    //创建的时间
    private Date createDate;
    //最后一次登录的时间
    private Date loginTime;
    //账号的状态,0是正常,1是锁定
    private Short status;



}

