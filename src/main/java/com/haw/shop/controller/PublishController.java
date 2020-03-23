package com.haw.shop.controller;

import com.haw.shop.service.ProductTypeService;
import com.haw.shop.service.UserService;
import com.haw.shop.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
public class PublishController {
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private UserService userService;

    @LoginToken
    @GetMapping("/router/publish")
    public ModelAndView category(HttpServletRequest request,ModelAndView modelAndView){
        //加载当前登录用户信息
        userService.loadUserInfo(request, modelAndView);
        productTypeService.loadFirstCard(modelAndView);
        modelAndView.setViewName("pages/product/category");
        return modelAndView;
    }

    @LoginToken
    @RequestMapping("/sell/publish")
    public ModelAndView publish(HttpServletRequest request,ModelAndView modelAndView, @RequestParam String productTypeIds){
        //加载当前登录用户信息
        userService.loadUserInfo(request, modelAndView);
        String[] ids = productTypeIds.split(",");
        productTypeService.publish(modelAndView,ids);
        modelAndView.setViewName("pages/product/publish");
        return modelAndView;
    }

}
