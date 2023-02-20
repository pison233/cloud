package com.pison.springcloud.service.impl;

import com.pison.springcloud.dao.StorageDao;
import com.pison.springcloud.service.StorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;


    @Override
    public void decrease(Long productId, Integer count) {
        String xid = RootContext.getXID();
        System.out.println("--------->xid_order:" +xid );
        log.info("--------->库存微服务开始减少库存，StorageServiceImpl");
        storageDao.decrease(productId,count);
        log.info("--------->库存微服务开始减少库存结束！！StorageServiceImpl");
    }
}
