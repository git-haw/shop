package com.haw.shop.service.impl;

import com.haw.shop.mapper.ProductTypeMapper;
import com.haw.shop.mapper.ShopMapper;
import com.haw.shop.mapper.UserInfoMapper;
import com.haw.shop.model.ProductType;
import com.haw.shop.model.Shop;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.ProductTypeService;
import com.haw.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haw on 17-8-30.
 */
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;


    @Override
    public int saveOrUpdate(ProductType productType) {
        if(productType.getId()==null){
            return productTypeMapper.insertSelective(productType);
        }else {
            return productTypeMapper.updateByPrimaryKeySelective(productType);
        }
    }

    @Override
    public List<ProductType> selectList(Integer parentId) {
        ProductType productType = new ProductType();
        productType.setParentId(parentId);
        return productTypeMapper.select(productType);
    }

    @Override
    public Integer countChildren(Integer parentId) {
        ProductType productType = new ProductType();
        productType.setParentId(parentId);
        Integer count = productTypeMapper.selectCount(productType);
        return count;
    }
}
