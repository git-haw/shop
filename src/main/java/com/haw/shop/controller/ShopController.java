package com.haw.shop.controller;

import com.haw.shop.model.Shop;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.ShopService;
import com.haw.shop.service.UserService;
import com.haw.shop.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;

    //    @LoginToken
    @RequestMapping("/view")
    public ModelAndView view(HttpServletRequest request, ModelAndView modelAndView) {
        HttpSession session = request.getSession();
        Integer userrid = (Integer) session.getAttribute("userid");
        UserInfo userInfo = userService.getUser(userrid);
        Integer shopId = userInfo.getShopId();
        Map map = new HashMap<>();
        Shop shop = new Shop();
        if (shopId != null) {
            shop = shopService.getShop(shopId);
            map.put("action", "/shop/setting");
        } else {
            map.put("action", "/shop/create");
        }
        map.put("shop", shop);
        modelAndView.addObject("result", map);
        modelAndView.setViewName("pages/shop/view");
        return modelAndView;
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request, Shop shop) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userid");
        shopService.create(shop, userId);
        return "/shop/view";
    }

    @RequestMapping("/setting")
    public String setting(Shop shop) {
        shopService.setting(shop);
        return "/shop/view";
    }


}
