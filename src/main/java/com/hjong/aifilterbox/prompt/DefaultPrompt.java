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
                "\n查看最近的消息，把我需要关注的发送到我的邮箱。如果没有值得关注的消息请直接回复“暂时没有需要看的”，不要回复其它多余的内容" +
                "\n最近消息如下：\n" + messages;
    };

    public String answerPrompt(String description, String messages){
        return description +
                "\n查看最近的消息，把我需要关注的发送到我的邮箱。如果没有值得关注的消息请直接回复“暂时没有需要看的”，不要回复其它多余的内容" +
                "\n最近消息如下：\n" + messages;
    }

}
