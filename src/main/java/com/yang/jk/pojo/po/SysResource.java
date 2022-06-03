package com.yang.jk.pojo.po;
import lombok.Data;
@Data
public class SysResource  {
    //主键
    private Integer id;
    //名称
    private String name;
    //链接地址
    private String url;
    //权限标识
    private String permission;
    //资源类型(0是目录，1是菜单，2是按钮)
    private Short type;
    //图标
    private String icon;
    //序号
    private Short sn;
    //父资源id
    private Integer parentId;



}

