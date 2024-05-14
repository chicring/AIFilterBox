package com.hjong.aifilterbox.prompt;

import org.springframework.stereotype.Component;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
@Component
public class DefaultPrompt {

    public String pushPrompt(String description, String messages){

        return description +
                "\n你是一个消息过滤助手，能甄别哪些消息用户需要关注。查看最近的消息，把我需要关注的消息推送给我。如果没有值得关注的消息请直接回复“暂时没有需要关注的”，不要回复其它多余的内容" +
                "\n最近消息如下：\n" + messages;
    };

    public String answerPrompt(String description, String messages){
        return description +
                "\n你是一个消息过滤助手，能甄别哪些消息用户需要关注。查看最近的消息，把我需要关注的消息推送给我。如果没有值得关注的消息请直接回复“暂时没有需要关注的”，不要回复其它多余的内容" +
                "\n最近消息如下：\n" + messages;
    }

}
