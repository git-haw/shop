package com.haw.shop.mapper;

import com.haw.shop.model.UserInfo;
import com.haw.shop.util.BaseDao;

public interface UserInfoMapper extends BaseDao<UserInfo> {

    int insertUser(UserInfo userInfo);
}