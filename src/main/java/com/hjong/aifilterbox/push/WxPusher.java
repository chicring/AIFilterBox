package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.entity.Message;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

@Component
public class WxPusher implements Push{

    @Resource
    OptionConfig optionConfig;


    @Override
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.wxpusher", durable = "true"),
            exchange = @Exchange(name = "push.direct", type = ExchangeTypes.DIRECT),
            key = {"wxpusher"}
    ))
    public void send(Map<String, String> data) {

    }

}
