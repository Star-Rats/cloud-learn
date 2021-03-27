package com.jmy.springcloud.service;

import com.jmy.springcloud.model.Payment;

public interface PaymentService {
    public int save(Payment payment);

    public Payment findById(Long id);

    public int delete(Long id);
}
