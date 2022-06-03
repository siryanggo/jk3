package com.yang.jk.service.impl;

import com.yang.jk.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther yhjStart
 * @create 2022-03-30 20:45
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void set(String key, Object obj) {
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();

        ops.set(key,obj,24*60, TimeUnit.MINUTES);
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    @Override
    public Boolean delete(String key) {
        Boolean flag = redisTemplate.delete(key);
        return flag;
    }

    /**
     * 更新所储存的对象的保存时间
     * 默认时间为1天
     * @param key
     * @return
     */
    @Override
    public Boolean updateExpir(String key) {
        Boolean flag = redisTemplate.expire(key, 24*60, TimeUnit.SECONDS);
        return flag;
    }

    /**
     * 自我设置更新储存对象的保存时间
     * @param key
     * @param time
     * @return
     */
    @Override
    public Boolean updateExpir(String key, long time) {
        Boolean flag = redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return flag;
    }

    /**
     * 根据key获取储存的值
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        ValueOperations ops = redisTemplate.opsForValue();
        Object obj = ops.get(key);
        return obj;
    }

    @Override
    public void permanentSet(String key, Object Obj) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key,Obj);
    }

    @Override
    public List<Object> batchGet(List<String> ids) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        List<Object> objects = ops.multiGet(ids);
        return objects;
    }

    @Override
    public boolean batchDel(List<String> token) {
        Long delete = redisTemplate.delete(token);
        if (delete>0) {
            return true;
        }
        return false;
    }


}
