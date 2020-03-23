package com.haw.shop.service;


import com.haw.shop.model.ProductType;
import com.haw.shop.model.UserInfo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by haw on 17-8-30.
 */
public interface ProductTypeService {
    int saveOrUpdate(ProductType productType);
    List<ProductType> selectList(Integer parentId);
    //统计后袋个数
    Integer countChildren(Integer parentId);
    void loadFirstCard(ModelAndView modelAndView);
    void publish(ModelAndView modelAndView, String[] productTypeIds);
}
