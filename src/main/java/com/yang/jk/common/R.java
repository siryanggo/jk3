package com.yang.jk.common;

import com.yang.jk.Exception.CommonException;
import com.yang.jk.enhance.Jsonable;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther yhjStart
 * @create 2022-03-27 23:22
 */
@Data
public class R extends Jsonable {
    private Boolean success;
    private String message;
    private Integer code;
    private Map<String,Object> data = new HashMap<>();
    private R() {

    }
    public static R ok() {
        R r = new R();
        r.success = true;
        r.setCode(1000);
        r.setMessage("成功");
        return r;
    }
    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(1001);
        r.setMessage("失败");
        return r;
    }
    //失败静态方法
    public static R error(String errInfo,Integer code) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(errInfo);
        return r;
    }
    public R success(Boolean success) {
        this.success = success;
        return this;
    }
    public R message(String message) {
        this.message = message;
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
    public static R raise(String message) {
        throw new CommonException(message);
    }
}
