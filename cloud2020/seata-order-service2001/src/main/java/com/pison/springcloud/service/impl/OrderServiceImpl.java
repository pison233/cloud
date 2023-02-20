package com.pison.springcloud.service.impl;

import com.pison.springcloud.dao.OrderDao;
import com.pison.springcloud.domain.Order;
import com.pison.springcloud.service.AccountService;
import com.pison.springcloud.service.OrderService;
import com.pison.springcloud.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        String xid = RootContext.getXID();
        System.out.println("--------->xid_order:" +xid );

        log.info("--------->开始新建订单，OrderServiceImpl");
        orderDao.create(order);

        log.info("--------->订单微服务开始调用库存，做库存扣减，OrderServiceImpl");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------->订单微服务开始调用库存，做库存扣减，结束！！OrderServiceImpl");

        log.info("--------->订单微服务开始调用账户,扣除相应金钱，OrderServiceImpl");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------->订单微服务开始调用账户,扣除相应金钱，结束！！OrderServiceImpl");

        log.info("--------->修改订单状态开始，OrderServiceImpl");
        orderDao.update(order.getUserId(),0);
        log.info("--------->修改订单状态结束！！OrderServiceImpl");

        log.info("--------->下订单完成！！OrderServiceImpl");
    }

}
