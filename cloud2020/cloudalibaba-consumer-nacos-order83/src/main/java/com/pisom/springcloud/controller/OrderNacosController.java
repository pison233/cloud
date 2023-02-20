package com.pisom.springcloud.controller;

import com.pisom.springcloud.service.ConsumerFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {

    @Resource
    private ConsumerFeignService ConsumerFeignService;

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/payment/nacos/ribbon/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }

    /**
     * 尝试整合feign
     */
    @GetMapping("/consumer/payment/nacos/feign/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return ConsumerFeignService.getPayment(id);
    }
}
