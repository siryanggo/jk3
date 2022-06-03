package com.yang.jk.shiro;

import com.yang.jk.Exception.CommonException;
import com.yang.jk.common.R;
import com.yang.jk.service.RedisService;
import com.yang.jk.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther yhjStart
 * @create 2022-03-31 16:52
 */
public class TokenFilter extends AccessControlFilter  {
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysUserService service;
    /**
     * 进入此方法，如果返回true则进入下一个controller 或者Filter,或者soringmvc拦截器
     * 返回false禁止访问,然后进入onAccessDenied方法
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**uhi
     * 返回false则禁止访问，返回true则进入下一个controller 或者Filter,或者soringmvc拦截器
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String token = req.getHeader("token");
        if (token==null) {
//            FilterR.error("没有token",req,resp);
            throw new Exception("没有token");
        }
        if (redisService.get(token)==null) {
//            FilterR.error("token失效",req,resp);
            throw new Exception("token失效");
        }
        /**
         * 延长token的存活时间
         */
//        redisService.updateExpir(token);
        SecurityUtils.getSubject().login(new Token(token));
        return true;
    }
}
