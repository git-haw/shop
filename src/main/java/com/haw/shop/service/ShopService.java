package com.haw.shop.service;


import com.haw.shop.model.Shop;

/**
 * Created by haw on 17-8-30.
 */
public interface ShopService {
    Shop getShop(Integer id);
    void create(Shop shop, Integer userId);
    void setting(Shop shop);
}
