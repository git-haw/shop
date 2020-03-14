package com.haw.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
public class ErrorPageController {

    @RequestMapping(value = "/error400Page")
    public String error400Page(Model model) {
        model.addAttribute("code","400");
        model.addAttribute("msg","错误的请求");
        return "/400";
    }
    @RequestMapping(value = "/error401Page")
    public String error401Page(Model model) {
        model.addAttribute("code","401");
        model.addAttribute("msg","未经授权");
        return "/401";
    }
    @RequestMapping(value = "/error404Page")
    public String error404Page(Model model) {
        model.addAttribute("code","404");
        model.addAttribute("msg","请求的页面不存在");
        return "/404";
    }
    @RequestMapping(value = "/error500Page")
    public String error500Page(Model model) {
        model.addAttribute("code","500");
        model.addAttribute("msg","内部服务器错误");
        return "/500";
    }
}
