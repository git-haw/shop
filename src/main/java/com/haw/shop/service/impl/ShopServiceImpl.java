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
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by haw on 17-8-30.
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Shop getShopByUserId(Integer userId) {
        Assert.notNull(userId, "用户id不能为空");
        Shop shop = new Shop();
        shop.setUserId(userId);
        return shopMapper.selectOne(shop);
    }

    @Override
    public void create(Shop shop) {
        shopMapper.insertSelective(shop);
    }

    @Override
    public void setting(Shop shop) {
        shopMapper.updateByPrimaryKey(shop);
    }
}
