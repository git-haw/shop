package com.haw.shop.service;


import com.haw.shop.model.UserInfo;
import com.haw.shop.vo.UserInfoVo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by haw on 17-8-30.
 */
public interface UserService {
    UserInfo getUser(Integer id);
    UserInfo register(UserInfo user);
    UserInfo findUserByLogin(String name, String password);
    UserInfoVo buildUserInfoVo(Integer userId);
    /**
     * 加载当前登录用户信息
     * @param request
     * @param modelAndView
     */
    void loadUserInfo(HttpServletRequest request,ModelAndView modelAndView);
}
