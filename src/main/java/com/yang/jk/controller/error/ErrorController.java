package com.yang.jk.controller.error;

import com.yang.jk.shiro.ErrorFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther yhjStart
 * @create 2022-04-01 11:29
 */
@RestController
public class ErrorController {
    /**
     * 过滤器异常处理方法
     * @param req
     */
    @RequestMapping(ErrorFilter.ERROR_URI)
    public void handle(HttpServletRequest req) throws Exception {
        /**
         * 异常对象
         */
        Object obj = req.getAttribute(ErrorFilter.ERROR_URI);
        throw (Exception) obj;

    }

}
