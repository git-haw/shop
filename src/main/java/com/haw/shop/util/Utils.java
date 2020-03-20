package com.haw.shop.util;

import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by aiwei on 2020-3-19.
 */
public class Utils {
    public static UserInfoVo buildUserInfoVo(Integer userId, UserService userService){
        UserInfoVo userInfoVo = new UserInfoVo();
        if(userId!=null){
            UserInfo userInfo = userService.getUser(userId);
            BeanUtils.copyProperties(userInfo, userInfoVo);
            userInfoVo.setIsLogin(true);
        }else{
            userInfoVo.setIsLogin(false);
        }
        return userInfoVo;
    }
}
