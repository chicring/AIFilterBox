package com.hjong.aifilterbox.api.gemini;

import com.hjong.aifilterbox.api.gemini.model.GeminiRequestBody;
import com.hjong.aifilterbox.api.gemini.model.GeminiResponseBody;
import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.util.JsonUtil;
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
    OptionConfig optionConfig;

    public GeminiResponseBody doCompletion(GeminiRequestBody requestBody){
        if(optionConfig.getOpenaiHost() == null || optionConfig.getOpenaiApiKey() == null){
            throw new RuntimeException("Gemini host or api key is null");
        }

        if (optionConfig.isGeminiEnableProxy()) {
            return restClientEnableProxy.post()
                    .uri(createUrl())
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponseBody.class);
        } else {
            return restClient.post()
                    .uri(createUrl())
                    .body(requestBody)
                    .retrieve()
                    .body(GeminiResponseBody.class);
        }
    }

    private String createUrl() {
        return optionConfig.getGeminiHost()
                + "/v1beta/models/"
                + optionConfig.getGeminiModel()
                + ":generateContent?key="
                + optionConfig.getGeminiApiKey();
    }
}
