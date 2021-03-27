package com.jmy.springcloud.controller;

import com.jmy.springcloud.feign.PaymentFeignService;
import com.jmy.springcloud.model.CommonResult;
import com.jmy.springcloud.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/consumer")
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @GetMapping("/payment/save")
    public CommonResult<Payment>  save(@RequestBody Payment payment){
        return paymentFeignService.save(payment);
    }

    @GetMapping("/payment/query/{id}")
    public CommonResult<Payment> findOne(@PathVariable("id") Long id){
        return paymentFeignService.findById(id);
    }

    @GetMapping("/port")
    public String testRibbon(){
        return paymentFeignService.getServerPort();
    }

    @GetMapping("/timeout")
    public String timeOut(){
        return paymentFeignService.testTimeOut();
    }
}
