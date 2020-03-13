package com.haw.shop.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.SqlServerMapper;

/**
 * 继承自己的BaseMapper
 * @author haw
 * @since 2017-9-5
 */
public interface BaseDao<T> extends Mapper<T>{
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
