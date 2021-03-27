package com.jmy.springcloud.controller;

import com.jmy.springcloud.model.CommonResult;
import com.jmy.springcloud.model.Payment;
import com.jmy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RequestMapping("/payment")
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public CommonResult<Payment> save(Payment payment) {
        int result = paymentService.save(payment);
        log.info("****插入结果 :" + result);

        if (result > 0) {
            return new CommonResult<Payment>(200, "新增成功！");
        } else {
            return new CommonResult<Payment>(201, "系统异常！");
        }
    }

    @GetMapping("query/{id}")
    public CommonResult<Payment> findById(@PathVariable("id") Long id){
        Payment payment = paymentService.findById(id);
        if (null != payment) {
            return new CommonResult<Payment>(200, "查询成功！", payment);
        } else {
            return new CommonResult<Payment>(201,"查无此Pay 测试热部署");
        }
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<Payment> delete(@PathVariable("id") Long id){
        int result = paymentService.delete(id);
        if (result > 0) {
            return new CommonResult<Payment>(200, "删除成功！");
        } else {
            return new CommonResult<Payment>(201, "删除失败！");
        }
    }

    @GetMapping("/port")
    public String getServerPort(){
        return port;
    }

    @GetMapping("/timeout")
    public String testTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.port;
    }

    @GetMapping("/get")
    public String gateWayT1(){
        return "come了~~come了~~" + port;
    }

    @GetMapping("/lb")
    public String gateWayT2(){
        return "LBWNB" + port;
    }
}
