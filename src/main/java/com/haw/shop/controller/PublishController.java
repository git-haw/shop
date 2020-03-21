package com.haw.shop.controller;

import com.haw.shop.model.ProductType;
import com.haw.shop.service.ProductTypeService;
import com.haw.shop.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
public class PublishController {
    @Autowired
    private ProductTypeService productTypeService;

    @LoginToken
    @GetMapping("/publish")
    public ModelAndView publish(HttpServletRequest request,ModelAndView modelAndView){
        List<ProductType> list0 = productTypeService.selectList(-1);
        List<ProductType> list1 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for(ProductType item: list0){
            List<ProductType> tmp = productTypeService.selectList(item.getId());
            list1.addAll(tmp);
        }
        for (ProductType item: list1){
            Integer count = productTypeService.countChildren(item.getId());
            list3.add(count);
        }
        modelAndView.addObject("list0", list0);
        modelAndView.addObject("list1", list1);
        modelAndView.addObject("list3", list3);
        modelAndView.setViewName("pages/product/publish");
        return modelAndView;
    }

}
