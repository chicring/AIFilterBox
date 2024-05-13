package com.hjong.aifilterbox.push;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import static com.hjong.aifilterbox.monitor.Constant.DEFAULT_MONITOR;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

@Component
public class PushFactory {

    @Resource
    EmailPush emailPush;

    @Resource
    WxPusher wxPusher;

    public Push getPush(String type){

        return switch (type) {
            case "mail" -> emailPush;
            case "wxPusher" -> wxPusher;
            default -> null;
        };
    }
}
