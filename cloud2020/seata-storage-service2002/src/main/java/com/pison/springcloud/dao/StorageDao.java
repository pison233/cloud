package com.pison.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    //扣除相应库存
    void decrease(@Param("productId")Long productId, @Param("count")Integer count);
}
