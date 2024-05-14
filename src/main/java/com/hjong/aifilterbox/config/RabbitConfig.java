package com.hjong.aifilterbox.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {

    @Bean("mailQueue")
    public Queue mailQueue() {
        return QueueBuilder
                .durable("mail")
                .build();
    }

//    @Bean("wxpusherQueue")
//    public Queue wxpusherQueue() {
//        return QueueBuilder
//                .durable("wxpusher")
//                .build();
//    }


}
