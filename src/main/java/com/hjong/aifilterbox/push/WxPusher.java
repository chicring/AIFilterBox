package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.entity.Message;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    public void send(Map<String, String> data) {

    }

    @Override
    public String buildHtmlContent(List<Message> messages){
        return null;
    }
}
