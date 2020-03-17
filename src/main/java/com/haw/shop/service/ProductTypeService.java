package com.haw.shop.service;


import com.haw.shop.model.ProductType;
import com.haw.shop.model.UserInfo;

import java.util.List;

/**
 * Created by haw on 17-8-30.
 */
public interface ProductTypeService {
    int saveOrUpdate(ProductType productType);
    List<ProductType> selectList(Integer parentId);
}
