package com.haw.shop.controller;

import com.haw.shop.model.Shop;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.ShopService;
import com.haw.shop.service.UserService;
import com.haw.shop.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
        Integer userId = (Integer) session.getAttribute("userid");
        Shop shop = shopService.getShopByUserId(userId);
        Map map = new HashMap<>();
        if (shop != null) {
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
    public String create(HttpServletRequest request, Shop shop) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userid");
        shop.setUserId(userId);
        shopService.create(shop);
        return "/shop/view";
    }

    @RequestMapping("/setting")
    public String setting(Shop shop) throws IOException {
        shopService.setting(shop);
        return "/shop/view";
    }

    @RequestMapping("/uploadLogo")
    @ResponseBody
    public String uploadLogo(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userid");
        if (!file.isEmpty()) {
            String fileName = userId + "_" + file.getOriginalFilename();
            file.transferTo(new File("E:/upload/shop/" + fileName));
        }
        return "";
    }


}
