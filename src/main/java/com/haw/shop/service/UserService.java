package com.haw.shop.service;


import com.haw.shop.model.UserInfo;

/**
 * Created by haw on 17-8-30.
 */
public interface UserService {
    UserInfo getUser(Integer id);
    UserInfo register(UserInfo user);
    UserInfo findUserByLogin(String name, String password);
}
