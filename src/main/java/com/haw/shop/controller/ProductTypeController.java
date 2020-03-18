package com.haw.shop.controller;

import com.haw.shop.model.ProductType;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.ProductTypeService;
import com.haw.shop.service.UserService;
import com.haw.shop.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aiwei on 2020-3-14.
 */
@Controller
@RequestMapping("/product_type")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    //    @LoginToken
    @RequestMapping("/view")
    public ModelAndView view(ModelAndView modelAndView) {
        Map map = new HashMap<>();
        List<ProductType> list0 = productTypeService.selectList(-1);
        List<ProductType> list1 = new ArrayList<>();
        for(ProductType item: list0){
            List<ProductType> tmp = productTypeService.selectList(item.getId());
            list1.addAll(tmp);
        }
        map.put("list0", list0);
        map.put("list1", list1);
        modelAndView.addObject("result", map);
        modelAndView.setViewName("pages/product_type/view");
        return modelAndView;
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
    public List load(Integer parentId){
        List list = productTypeService.selectList(parentId);
        return list;
    }
}
