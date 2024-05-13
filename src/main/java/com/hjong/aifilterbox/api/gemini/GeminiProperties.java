package com.hjong.aifilterbox.api.gemini;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.mapper.OptionMapper;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import static com.hjong.aifilterbox.entity.Constant.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/
@Component
@Data
public class GeminiProperties implements InitializingBean {

    /**
     * OpenAI API Key
     */
    private String apiKey;
    /**
     * OpenAI API URL
     */
    private String apiUrl;
    /**
     * OpenAI API Model
     */
    private String model;
    /**
     * 是否启用代理
     */
    private boolean enableProxy;
    /**
     * 代理主机
     */
    private String proxyHost;
    /**
     * 代理端口
     */
    private Integer proxyPort;

    @Resource
    OptionMapper optionMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        optionMapper.selectList(new QueryWrapper<Option>().eq("type", GEMINI_TYPE))
                .forEach(option -> {
                    switch (option.getKey()) {
                        case GEMINI_API_KEY:
                            apiKey = option.getValue();
                            break;
                        case GEMINI_HOST:
                            apiUrl = option.getValue();
                            break;
                        case GEMINI_Model:
                            model = option.getValue();
                            break;
                        case GEMINI_Enable_Proxy:
                            enableProxy = Boolean.parseBoolean(option.getValue());
                            break;
                        case GEMINI_Proxy_Host:
                            proxyHost = option.getValue();
                            break;
                        case GEMINI_Proxy_Port:
                            proxyPort = Integer.parseInt(option.getValue());
                            break;
                    }
                });
    }
}
