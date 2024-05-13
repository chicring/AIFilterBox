package com.hjong.aifilterbox.action;

import com.hjong.aifilterbox.api.gemini.GeminiProperties;
import com.hjong.aifilterbox.api.openai.OpenAiApi;
import com.hjong.aifilterbox.api.openai.OpenAiProperties;
import com.hjong.aifilterbox.api.openai.model.OpenAiRequestBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;
import com.hjong.aifilterbox.config.BeanConfig;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.prompt.DefaultPrompt;
import com.hjong.aifilterbox.push.Push;
import com.hjong.aifilterbox.push.PushFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/

@Slf4j
@Component
public class PushAction implements Action {

    @Resource
    OpenAiApi openAiApi;

    @Resource
    PushFactory pushFactory;

    @Resource
    DefaultPrompt defaultPrompt;

    @Resource
    OpenAiProperties openAiProperties;

    @Resource
    GeminiProperties geminiProperties;

    @Resource
    BeanConfig beanConfig;

    @Override
    public void doAction(List<Message> messages,String ...args) {

        String pushType = args[0];
        String prompt = args[1];

        String pushPrompt = defaultPrompt.pushPrompt(prompt, getContent(messages));

        OpenAiResponseBody openAiResponseBody = openAiApi.doCompletion(OpenAiRequestBody.builder(pushPrompt,beanConfig.getOpenaiModel()));

        log.info("reply: {}",openAiResponseBody.getChoices().getFirst().getMessage().getContent());

        doPush("推送",openAiResponseBody.getChoices().getFirst().getMessage().getContent(),pushType);
    }

    private String getContent(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        messages.forEach(message -> {
            sb.append(message.getMessageId());
            sb.append(" ");
            sb.append(message.getTitle());
            sb.append(" ");
            if (message.getDescription() != null) {
                sb.append(message.getDescription());
                sb.append(" ");
            }
            if (message.getTypeName() != null) {
                sb.append(message.getTypeName());
            }
            sb.append("\n");
        });
        return sb.toString();
    }

    private void doPush(String title,String content,String pushType) {
        Push push = pushFactory.getPush(pushType);
        push.send(title,content);
    }
}
