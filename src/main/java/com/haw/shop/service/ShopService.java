package com.haw.shop.service;


import com.haw.shop.model.Shop;

/**
 * Created by haw on 17-8-30.
 */
public interface ShopService {
    Shop getShopByUserId(Integer userId);
    void create(Shop shop);
    void setting(Shop shop);
}
