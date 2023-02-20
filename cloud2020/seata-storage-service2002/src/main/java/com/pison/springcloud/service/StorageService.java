package com.pison.springcloud.service;

public interface StorageService {

    //扣除相应库存
    void decrease(Long productId, Integer count);
}
