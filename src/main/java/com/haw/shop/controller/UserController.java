package com.haw.shop.controller;

import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.token.TokenUtil;
import com.haw.shop.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public UserInfo test(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping("/register")
    public String register(UserInfo user) {
        userService.register(user);
        return "/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(String name, String password, HttpServletRequest request) {
        UserInfo userInfo = userService.findUserByLogin(name, password);
        Map result = new HashMap<>();
        if (userInfo != null) {
            String token = TokenUtil.getToken(userInfo.getId());
            HttpSession session = request.getSession();
            session.setAttribute("userid", userInfo.getId());
            session.setAttribute("token", token);
            result.put("userid", userInfo.getId());
            result.put("token", token);
            result.put("flag", 1);
        } else {
            result.put("flag", 0);
        }
        return result;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("/login");
    }

    @LoginToken
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage() {
        return "你已通过验证";
    }

}
