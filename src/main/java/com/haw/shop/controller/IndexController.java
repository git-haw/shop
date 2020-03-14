package com.haw.shop.controller;

import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session = request.getSession();
        Integer userrid = (Integer)session.getAttribute("userid");
        Map result = new HashMap<>();
        if(userrid!=null){
            UserInfo userInfo = userService.getUser(userrid);
            UserInfoVo userInfoVo = new UserInfoVo();
            BeanUtils.copyProperties(userInfo,userInfoVo);
            result.put("flag", 1);
            result.put("userInfo",userInfoVo);
        }else{
            result.put("flag",0);
        }
        modelAndView.addObject("result",result);
        return modelAndView;
    }
}
