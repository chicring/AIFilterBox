package com.hjong.aifilterbox.action;

import com.hjong.aifilterbox.api.openai.OpenAiApi;
import com.hjong.aifilterbox.entity.Message;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/

@Component
public class PushAction implements Action {

    @Resource
    OpenAiApi openAiApi;

    @Override
    public void doAction(List<Message> messages) {
        System.out.println("PushAction doAction");
    }
}
