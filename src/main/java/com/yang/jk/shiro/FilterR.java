package com.yang.jk.shiro;

import com.yang.jk.common.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther yhjStart
 * @create 2022-03-31 21:30
 */
public class FilterR {
    public static void   error(String message,HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json; charset=UTF-8");
        resp.setStatus(400);
        resp.getWriter().write(R.error().message(message).JsonString());
    }
}
