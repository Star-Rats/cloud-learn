package com.jmy.springcloud.feign;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService{
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "不要迷恋哥，哥只是传说";
    }
}
