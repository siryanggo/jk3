package com.yang.jk.shiro;

import javax.servlet.*;
import java.io.IOException;

/**
 * @auther yhjStart
 * @create 2022-04-01 10:53
 */
public class ErrorFilter implements Filter {
    public static final String  ERROR_URI = "/errorHandle";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("ErrorFilter执行了");
        try {
            chain.doFilter(request,response);
        }catch (Exception e) {
            request.setAttribute(ERROR_URI,e);
            request.getRequestDispatcher(ERROR_URI).forward(request,response);
        }
    }
}
