package com.pison.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String TestA() throws InterruptedException {
//        int a = 1 / 0;
        Thread.sleep(300);
        return "---------TestA";
    }

    @GetMapping("/testB")
    public String TestB() {
        return "---------TestB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "---------HotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "---------deal_testHotKeyT.T";

    }
}
