package com.hjong.aifilterbox.api.gemini;

import com.hjong.aifilterbox.api.gemini.model.GeminiRequestBody;
import com.hjong.aifilterbox.api.gemini.model.GeminiResponseBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/
@Component
public class GeminiApi {
    @Resource
    RestClient restClient;

    @Resource
    RestClient restClientEnableProxy;


    public GeminiResponseBody doCompletion(GeminiRequestBody requestBody, GeminiProperties properties){
        if (properties.isEnableProxy()) {
            return restClientEnableProxy.post()
                    .uri(properties.getApiUrl())
                    .header("Authorization", "Bearer " + properties.getApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponseBody.class);
        } else {
            return restClient.post()
                    .uri(properties.getApiUrl())
                    .header("Authorization", "Bearer " + properties.getApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponseBody.class);
        }
    }
}
