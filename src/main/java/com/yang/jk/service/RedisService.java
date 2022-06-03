package com.yang.jk.service;

import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-03-30 20:40
 */
public interface RedisService {
    void set(String key,Object obj);
    Boolean delete(String key);
    Boolean updateExpir(String key);
    Boolean updateExpir(String key,long time);
    Object get(String key);
    void permanentSet(String key,Object Obj);
    List<Object> batchGet(List<String> ids);
    boolean batchDel(List<String> token);
}
