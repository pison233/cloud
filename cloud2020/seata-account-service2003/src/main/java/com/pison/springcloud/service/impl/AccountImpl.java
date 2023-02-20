package com.pison.springcloud.service.impl;

import com.pison.springcloud.dao.AccountDao;
import com.pison.springcloud.domain.CommonResult;
import com.pison.springcloud.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountImpl implements AccountService{
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        String xid = RootContext.getXID();
        System.out.println("--------->xid_order:" +xid );
        log.info("--------->账户微服务开始扣除金钱，AccountImpl");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId,money);
        log.info("--------->账户微服务扣除金钱结束！！AccountImpl");

    }
}
