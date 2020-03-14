package com.haw.shop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by aiwei on 2020-3-14.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error"; // 定义错误显示页，error.html

    // 出现异常之后会跳转到此方法
    @ExceptionHandler(Exception.class) // 所有的异常都是Exception子类
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW); // 设置跳转路径
        ErrorInfo info = new ErrorInfo() ;
        info.setCode(response.getStatus());     // 标记一个错误信息类型
        info.setMessage(e.getMessage());
        info.setUrl(request.getRequestURL().toString());
        mav.addObject("errorInfo", info);
        return mav;
    }
}
