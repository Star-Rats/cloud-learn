package com.jmy.springcloud.mapper;

import com.jmy.springcloud.model.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    int save(Payment payment);

    Payment findById(@Param("id") Long id);

    int delete(@Param("id") Long id);

}
