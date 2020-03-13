package com.haw.shop.service;


import com.haw.shop.model.UserInfo;

/**
 * Created by haw on 17-8-30.
 */
public interface UserService {
    UserInfo getUser(Long id);
    UserInfo register(UserInfo user);
    boolean login(String name, String password);
}
