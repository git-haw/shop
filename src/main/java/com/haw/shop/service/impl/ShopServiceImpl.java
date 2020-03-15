package com.haw.shop.service.impl;

import com.haw.shop.mapper.ShopMapper;
import com.haw.shop.mapper.UserInfoMapper;
import com.haw.shop.model.Shop;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.ShopService;
import com.haw.shop.service.UserService;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by haw on 17-8-30.
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Shop getShop(Integer id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public void create(Shop shop, Integer userId) {
        shopMapper.insertSelective(shop);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        UserInfo vo_userInfo = new UserInfo();
        vo_userInfo.setId(userId);
        vo_userInfo.setShopId(shop.getId());
        userInfoMapper.updateByPrimaryKeySelective(vo_userInfo);
    }

    @Override
    public void setting(Shop shop) {
        shopMapper.updateByPrimaryKey(shop);
    }
}
