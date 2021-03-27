package com.jmy.springcloud.feign;

import com.jmy.springcloud.model.CommonResult;
import com.jmy.springcloud.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @PostMapping("/payment/save")
    public CommonResult<Payment> save(Payment payment);

    @GetMapping("/payment/query/{id}")
    public CommonResult<Payment> findById(@PathVariable("id") Long id);

    @DeleteMapping("/payment/delete/{id}")
    public CommonResult<Payment> delete(@PathVariable("id") Long id);

    @GetMapping("/payment/port")
    public String getServerPort();

    @GetMapping("/payment/timeout")
    public String testTimeOut();
}
