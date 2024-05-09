package com.hjong.aifilterbox.api.openai;

import com.hjong.aifilterbox.api.openai.model.OpenAiRequestBody;
import com.hjong.aifilterbox.api.openai.model.OpenAiResponseBody;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
public class OpenAiApi {

    public OpenAiResponseBody doCompletion(OpenAiRequestBody requestBody,OpenAiProperties properties){


        if (properties.getEnableProxy()) {

            return null;
        }else {

        }
    }
}
