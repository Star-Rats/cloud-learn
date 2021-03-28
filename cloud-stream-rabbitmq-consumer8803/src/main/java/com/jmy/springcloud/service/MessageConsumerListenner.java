package com.jmy.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(Sink.class)
public class MessageConsumerListenner {
    @Value("server.port")
    private String port;

    @StreamListener(Sink.INPUT)
    private void consumerMessage(Message<String> message){
        log.info("消费者2号,----->接受到的消息: "+message.getPayload()+"\t  port: "+port);
    }
}
