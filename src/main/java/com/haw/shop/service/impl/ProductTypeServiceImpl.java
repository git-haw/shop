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
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

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
        Assert.notNull(parentId,"父商品分类id不能为空");
        ProductType productType = new ProductType();
        productType.setParentId(parentId);
        return productTypeMapper.select(productType);
    }

    @Override
    public Integer countChildren(Integer parentId) {
        Assert.notNull(parentId,"父商品分类id不能为空");
        ProductType productType = new ProductType();
        productType.setParentId(parentId);
        Integer count = productTypeMapper.selectCount(productType);
        return count;
    }

    @Override
    public void loadFirstCard(ModelAndView modelAndView) {
        List<ProductType> list0 = this.selectList(-1);
        List<ProductType> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (ProductType item : list0) {
            List<ProductType> tmp = this.selectList(item.getId());
            list1.addAll(tmp);
        }
        for (ProductType item : list1) {
            Integer count = this.countChildren(item.getId());
            list2.add(count);
        }
        modelAndView.addObject("list0", list0);
        modelAndView.addObject("list1", list1);
        modelAndView.addObject("list2", list2);
    }

    @Override
    public void publish(ModelAndView modelAndView, String[] productTypeIds) {
        List<ProductType> list = new ArrayList<ProductType>();
        for (String id:productTypeIds){
            ProductType productType = productTypeMapper.selectByPrimaryKey(Integer.valueOf(id));
            list.add(productType);
        }
        modelAndView.addObject("categoryList",list);
    }
}
