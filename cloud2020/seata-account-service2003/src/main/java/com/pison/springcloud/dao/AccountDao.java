package com.pison.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    //账户扣钱
    void decrease(@Param("userId")Long userId, @Param("money") BigDecimal money);
}
