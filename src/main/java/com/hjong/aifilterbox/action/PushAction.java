package com.hjong.aifilterbox.action;


import com.hjong.aifilterbox.api.gemini.GeminiApi;
import com.hjong.aifilterbox.api.gemini.model.GeminiRequestBody;
import com.hjong.aifilterbox.api.gemini.model.GeminiResponseBody;
import com.hjong.aifilterbox.api.openai.OpenAiApi;
import com.hjong.aifilterbox.api.openai.model.OpenAiRequestBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;
import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.prompt.DefaultPrompt;
import com.hjong.aifilterbox.push.Push;
import com.hjong.aifilterbox.push.PushFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    GeminiApi geminiApi;

    @Resource
    AmqpTemplate rabbitTemplate;

    @Resource
    PushFactory pushFactory;

    @Resource
    DefaultPrompt defaultPrompt;

    @Resource
    OptionConfig optionConfig;

    @Override
    public void doAction(List<Message> messages,String ...args) {

        String pushType = args[0];
        String prompt = args[1];

        String pushPrompt = defaultPrompt.pushPrompt(prompt, getContent(messages));

        List<String> sendMessageIds;

        if (optionConfig.getAiChannel().equals("openai")){
            sendMessageIds = doCompletionByOpenAI(pushPrompt);
        }else {
           sendMessageIds = doCompletionByGemini(pushPrompt);
        }

        if (sendMessageIds.isEmpty()){
            log.info("没有需要关注的消息");
        }else {

            List<Message> pushMessages = messages.stream().filter(message -> sendMessageIds.contains(message.getMessageId())).toList();

            Push push = pushFactory.getPush(pushType);
            rabbitTemplate.convertAndSend(pushType, Map.of("title", "消息推送", "content", push.buildHtmlContent(pushMessages)));
//            doPush("消息推送",pushMessages,pushType);
        }

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

    private void doPush(String title,List<Message> messages,String pushType) {
        Push push = pushFactory.getPush(pushType);
        push.send(Map.of("title", title, "content", push.buildHtmlContent(messages)));
    }


    private List<String> doCompletionByGemini(String content){
        GeminiRequestBody.Tool tool = new GeminiRequestBody.Tool();

        GeminiRequestBody.Tool.Function_declarations function = new GeminiRequestBody.Tool.Function_declarations();
        function.setName("pushToUser");
        function.setDescription("将消息推送到用户，根据传入的消息id进行推送");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        parameters.put("properties", Map.of("messageIds", Map.of("type", "string", "description", "消息id列表,例如[xbk-56,bili-34]和[xbk-54]")));
        parameters.put("required", List.of("messageIds"));

        function.setParameters(parameters);

        tool.setFunction_declarations(function);

        GeminiResponseBody geminiResponseBody = geminiApi.doCompletion(GeminiRequestBody.builder(content, List.of(tool)));

        if(geminiResponseBody.getCandidates().getFirst().getContent().getParts().getFirst().getFunctionCall() != null){
            Map<String, Object> args = geminiResponseBody.getCandidates().getFirst().getContent().getParts().getFirst().getFunctionCall().getArgs();
            log.info("推送消息id:{}",args.get("messageIds"));
            return (List<String>) args.get("messageIds");
        }
        return List.of();
    }

    private List<String> doCompletionByOpenAI(String content){
        OpenAiResponseBody openAiResponseBody = openAiApi.doCompletion(OpenAiRequestBody.builder(content, optionConfig.getOpenaiModel()));

        return List.of();
    }
}
