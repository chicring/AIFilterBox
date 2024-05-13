package com.hjong.aifilterbox.api.openai;

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
 * @date 2024/5/9
 **/
@Component
@Data
public class OpenAiProperties implements InitializingBean {
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
        optionMapper.selectList(new QueryWrapper<Option>().eq("type", OpenAI_TYPE))
                .forEach(option -> {
                    switch (option.getKey()) {
                        case OpenAI_API_KEY:
                            apiKey = option.getValue();
                            break;
                        case OpenAI_HOST:
                            apiUrl = option.getValue();
                            break;
                        case OpenAI_Model:
                            model = option.getValue();
                            break;
                        case OpenAI_Enable_Proxy:
                            enableProxy = Boolean.parseBoolean(option.getValue());
                            break;
                        case OpenAI_Proxy_Host:
                            proxyHost = option.getValue();
                            break;
                        case OpenAI_Proxy_Port:
                            proxyPort = Integer.parseInt(option.getValue());
                            break;
                    }
                });
    }
}
