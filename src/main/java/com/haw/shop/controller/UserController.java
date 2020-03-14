package com.haw.shop.controller;

import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.token.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public UserInfo test(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }

    @RequestMapping("/register")
    public String register(UserInfo user){
        userService.register(user);
        return "/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String name, String password){
        String token = userService.login(name,password);
        return token;
}

    @UserLoginToken
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage(){
        return "你已通过验证";
    }

}
