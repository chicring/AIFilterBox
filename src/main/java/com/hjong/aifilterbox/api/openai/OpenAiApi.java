package com.hjong.aifilterbox.api.openai;

import com.hjong.aifilterbox.api.openai.model.OpenAiRequestBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;
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

    public OpenAiResponseBody doCompletion(OpenAiRequestBody requestBody,OpenAiProperties properties){

        if (properties.isEnableProxy()) {
            return restClientEnableProxy.post()
                    .uri(properties.getApiUrl())
                    .header("Authorization", "Bearer " + properties.getApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(OpenAiResponseBody.class);
        } else {
            return restClient.post()
                    .uri(properties.getApiUrl())
                    .header("Authorization", "Bearer " + properties.getApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(OpenAiResponseBody.class);
        }
    }
}
