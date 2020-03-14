package com.haw.shop.service.impl;

import com.haw.shop.mapper.UserInfoMapper;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by haw on 17-8-30.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUser(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo register(UserInfo user) {
        user.setRegisterDate(new Date());
        userInfoMapper.insertUser(user);
        return user;
    }

    @Override
    public String login(String name, String password) {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName(name);
        userInfo1.setPassword(password);
        UserInfo rst1 = userInfoMapper.selectOne(userInfo1);
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setEmail(name);
        userInfo2.setPassword(password);
        UserInfo rst2 = userInfoMapper.selectOne(userInfo2);
        UserInfo userInfo3 = new UserInfo();
        userInfo3.setPhone(name);
        userInfo3.setPassword(password);
        UserInfo rst3 = userInfoMapper.selectOne(userInfo3);

        if (rst1 != null || rst3 != null || rst2 != null) {
            UserInfo userInfo = (rst1 != null ? rst1 : (rst3 != null ? rst3 : rst2));
            String token = TokenUtil.getToken(userInfo.getId());
            return token;
        }
        return "";
    }
}
