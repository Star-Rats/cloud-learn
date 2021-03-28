package com.jmy.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider{
    @Resource
    private MessageChannel output; // 消息发送管道，与Binder层交互
    @Override
    public String send() {
        String message = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(message).build());
        log.info("准备发送消息：" + message);
        return null;
    }
}
