package com.hjong.aifilterbox.api.openai;

import lombok.Data;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
@Data
public class OpenAiProperties {
    /**
     * OpenAI API Key
     */
    private String apiKey;
    /**
     * OpenAI API URL
     */
    private String apiUrl;
    /**
     * 是否启用代理
     */
    private Boolean enableProxy;
    /**
     * 代理主机
     */
    private String proxyHost;
    /**
     * 代理端口
     */
    private Integer proxyPort;
}
