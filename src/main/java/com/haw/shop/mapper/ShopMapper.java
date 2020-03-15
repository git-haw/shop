package com.haw.shop.mapper;

import com.haw.shop.model.Shop;
import com.haw.shop.util.BaseDao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ShopMapper extends BaseDao<Shop> {
}