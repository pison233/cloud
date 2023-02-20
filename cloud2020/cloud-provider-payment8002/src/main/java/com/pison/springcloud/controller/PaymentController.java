package com.pison.springcloud.controller;

import com.pison.springcloud.entities.CommonResult;
import com.pison.springcloud.entities.Payment;
import com.pison.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        return result > 0 ? new CommonResult(200, "插入成功,端口号：" + serverPort, 1) : new CommonResult(444, "插入失败",null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果：11" + payment);
        return payment != null ? new CommonResult(200, "查询成功,端口号：" + serverPort, payment) : new CommonResult(444, "没有对应记录，查询ID:" + id, null);
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
