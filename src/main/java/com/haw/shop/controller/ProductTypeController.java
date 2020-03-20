package com.haw.shop.controller;

import com.haw.shop.model.ProductType;
import com.haw.shop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aiwei on 2020-3-14.
 */
@Validated
@Controller
@RequestMapping("/product_type")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    //    @LoginToken
    @RequestMapping("/view")
    public ModelAndView view(ModelAndView modelAndView) {
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
        modelAndView.setViewName("pages/product_type/view");
        return modelAndView;
    }

    //    @LoginToken
    @RequestMapping("/countChildren")
    @ResponseBody
    public Integer countChildren(@NotNull(message = "父商品分类id不能为空")Integer parentId){
        Integer count = productTypeService.countChildren(parentId);
        return count;
    }

    //    @LoginToken
    @RequestMapping("/save")
    @ResponseBody
    public Map saveOrUpdate(ProductType productType){
        int i = productTypeService.saveOrUpdate(productType);
        Map map = new HashMap<>();
        if(i == 1){
            map.put("code", 1);
            map.put("msg", "保存成功");
        }else {
            map.put("code", -1);
            map.put("msg", "保存失败，请重试");
        }
        return map;
    }

    //    @LoginToken
    @RequestMapping("/load")
    @ResponseBody
    public List load(@NotNull(message = "父商品分类id不能为空")Integer parentId){
        List list = productTypeService.selectList(parentId);
        return list;
    }
}
