package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.config.OptionConfig;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

@Data
@Component
public class WxPusher implements Push{

    @Resource
    OptionConfig optionConfig;


    @Override
    public void send(String title, String content) {

    }
}
