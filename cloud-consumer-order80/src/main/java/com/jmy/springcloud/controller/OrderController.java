package com.jmy.springcloud.controller;

import com.jmy.springcloud.model.CommonResult;
import com.jmy.springcloud.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RequestMapping("/consumer")
@RestController
@Slf4j
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment";

    @GetMapping("/payment/save")
    public CommonResult<Payment>  save(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/save",payment,CommonResult.class);
    }

    @GetMapping("/payment/query/{id}")
    public CommonResult<Payment> findOne(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/query/" + id,CommonResult.class);
    }

    @GetMapping("/port")
    public String testRibbon(){
        return restTemplate.getForObject(PAYMENT_URL + "/port",String.class);
    }
}
