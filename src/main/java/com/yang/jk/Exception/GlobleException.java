package com.yang.jk.Exception;

import com.yang.jk.common.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther yhjStart
 * @create 2022-03-29 15:24
 */
@ControllerAdvice
public class GlobleException {

//    @ExceptionHandler(Throwable.class)
//    public void handle(Throwable t, HttpServletRequest req, HttpServletResponse resp) throws Exception{
//        resp.setContentType("application/json; charset=UTF-8");
//        resp.setStatus(400);
//        resp.getWriter().write(Rs.error(t).JsonString());
//        Debugs.run(t::printStackTrace);
////        t.printStackTrace();
//    }
    @ExceptionHandler(Exception.class)
    public void handle(Exception t, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        t.printStackTrace();
        if (t instanceof ServletException) {
            resp.setContentType("application/json; charset=UTF-8");
            resp.setStatus(400);
            resp.getWriter().write(R.error().message(t.getCause().getMessage()).JsonString());
        }else if(t instanceof AuthorizationException) {
            resp.setContentType("application/json; charset=UTF-8");
            resp.setStatus(400);
            resp.getWriter().write(R.error().message("权限不够").JsonString());
        }

        else  {
            resp.setContentType("application/json; charset=UTF-8");
            resp.setStatus(400);
            resp.getWriter().write(R.error().message(t.getMessage()).JsonString());
        }

    }
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public R handle(CommonException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }


}
