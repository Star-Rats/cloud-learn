package com.jmy.springcloud.service.impl;

import com.jmy.springcloud.mapper.PaymentMapper;
import com.jmy.springcloud.model.Payment;
import com.jmy.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;
    @Override
    public int save(Payment payment) {
        return paymentMapper.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentMapper.findById(id);
    }

    @Override
    public int delete(Long id) {
        return paymentMapper.delete(id);
    }
}
