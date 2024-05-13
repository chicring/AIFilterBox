package com.hjong.aifilterbox.api.gemini;

import com.hjong.aifilterbox.api.gemini.model.GeminiRequestBody;
import com.hjong.aifilterbox.api.gemini.model.GeminiResponseBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;
import com.hjong.aifilterbox.config.BeanConfig;
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

    @Resource
    BeanConfig beanConfig;

    public GeminiResponseBody doCompletion(GeminiRequestBody requestBody){
        if (beanConfig.isGeminiEnableProxy()) {
            return restClientEnableProxy.post()
                    .uri(beanConfig.getGeminiHost())
                    .header("Authorization", "Bearer " + beanConfig.getGeminiApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponseBody.class);
        } else {
            return restClient.post()
                    .uri(beanConfig.getGeminiHost())
                    .header("Authorization", "Bearer " + beanConfig.getGeminiApiKey())
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponseBody.class);
        }
    }
}
