package com.pison.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pison.springcloud.entities.CommonResult;
import com.pison.springcloud.entities.Payment;
import com.pison.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试", new Payment(2023L, "serial001"));
    }

    public CommonResult handleException(BlockException blockException) {
        return new CommonResult(444, blockException.getClass().getCanonicalName() + "\t服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource("byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按url限流测试OK", new Payment(2023L, "serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = {CustomerBlockHandler.class},
            blockHandler = "blockHandler2"
    )
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按自定义限流OK", new Payment(2023L, "serial003"));
    }
}
