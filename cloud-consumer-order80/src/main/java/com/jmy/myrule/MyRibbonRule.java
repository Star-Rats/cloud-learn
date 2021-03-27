package com.jmy.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 修改Ribbon负载均衡策略配置类
public class MyRibbonRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
