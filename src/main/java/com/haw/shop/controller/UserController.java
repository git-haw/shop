package com.haw.shop.controller;

import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.token.TokenUtil;
import com.haw.shop.token.LoginToken;
import com.haw.shop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haw on 17-8-30.
 */
@Validated
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(UserInfo user) {
        userService.register(user);
        return "/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(@NotNull(message = "登录名不能为空") String name, @NotNull(message = "登录密码不能为空") String password,
                     HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = userService.findUserByLogin(name, password);
        Map result = new HashMap<>();
        if (userInfo != null) {
            String token = TokenUtil.getToken(userInfo.getId());
            HttpSession session = request.getSession();
            session.setAttribute("userid", userInfo.getId());
            session.setAttribute("token", token);
            Utils.addCookie(response, "userid", userInfo.getId().toString(), 3600);
            Utils.addCookie(response, "token", token, 3600);
            result.put("flag", 1);
        } else {
            result.put("flag", 0);
        }
        return result;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        Utils.removeCookie(response, "userid");
        Utils.removeCookie(response,"token");
        return new ModelAndView("/login");
    }

}
