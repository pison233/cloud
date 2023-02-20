package com.pison.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pison.springcloud.entities.CommonResult;
import com.pison.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class CustomerBlockHandler {

    public static CommonResult blockHandler(BlockException blockException){
        return new CommonResult(4444,"按自定义限流的默认全局fallback");
    }

    public static CommonResult blockHandler2(BlockException blockException){
        return new CommonResult(4444,"按自定义限流的默认全局fallback 2号");
    }
}
