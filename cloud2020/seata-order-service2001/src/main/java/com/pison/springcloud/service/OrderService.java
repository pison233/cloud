package com.pison.springcloud.service;

import com.pison.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    //新建订单
    void create(Order order);
}
