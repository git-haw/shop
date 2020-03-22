package com.haw.shop.controller;

import com.haw.shop.service.UserService;
import com.haw.shop.token.PassToken;
import com.haw.shop.util.Utils;
import com.haw.shop.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @PassToken
    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request,ModelAndView modelAndView){
        //加载当前登录用户信息
        Utils.loadUserInfo(request, modelAndView, userService);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PassToken
    @GetMapping("/register")
    public ModelAndView register(HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userid");
        UserInfoVo userInfoVo = Utils.buildUserInfoVo(userId, userService);
        modelAndView.addObject("userInfo",userInfoVo);
        return modelAndView;
    }
}
