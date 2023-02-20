package com.pison.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "*****PaymentFallbackService的paymentInfo_OK方法的fallbackT.T";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "*****PaymentFallbackService的paymentInfo_TimeOut方法的fallbackT.T";
    }
}
