package com.haw.shop.controller;

import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by haw on 17-8-30.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    @ResponseBody
    public UserInfo test(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

    @RequestMapping("/register")
    public String register(UserInfo user){
        userService.register(user);
        return "/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(String name, String password){
        boolean loginResult = userService.login(name,password);
        return loginResult;
    }

}
