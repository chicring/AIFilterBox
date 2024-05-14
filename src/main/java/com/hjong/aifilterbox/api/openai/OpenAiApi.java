package com.hjong.aifilterbox.api.openai;

import com.hjong.aifilterbox.api.openai.model.OpenAiRequestBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;
import com.hjong.aifilterbox.config.OptionConfig;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
@Component
public class OpenAiApi {

    @Resource
    RestClient restClient;

    @Resource
    RestClient restClientEnableProxy;

    @Resource
    OptionConfig optionConfig;

    public OpenAiResponseBody doCompletion(OpenAiRequestBody requestBody){
        if(optionConfig.getOpenaiHost() == null || optionConfig.getOpenaiApiKey() == null){
            throw new RuntimeException("openai host or api key is null");
        }

        if (optionConfig.isOpenaiEnableProxy()) {
            return restClientEnableProxy.post()
                    .uri(optionConfig.getOpenaiHost() + "/v1/chat/completions")
                    .header("Authorization", "Bearer " + optionConfig.getOpenaiApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(OpenAiResponseBody.class);
        } else {
            return restClient.post()
                    .uri(optionConfig.getOpenaiHost() + "/v1/chat/completions")
                    .header("Authorization", "Bearer " + optionConfig.getOpenaiApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(OpenAiResponseBody.class);
        }
    }
}
