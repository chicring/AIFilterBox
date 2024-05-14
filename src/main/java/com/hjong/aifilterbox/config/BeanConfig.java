package com.hjong.aifilterbox.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.mapper.OptionMapper;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/

@Data
@Configuration
public class BeanConfig implements InitializingBean {

    @Resource
    OptionMapper optionMapper;

    public String mailHost;
    public String mailPort;
    public String mailUsername;
    public String mailPassword;
    public String mailTo;

    public String wxpusherAppToken;
    public String wxpusherUids;
    public String wxpusherTopicIds;

    public String geminiApiKey;
    public String geminiHost;
    public String geminiModel;
    public boolean geminiEnableProxy;
    public String geminiProxyHost;
    public String geminiProxyPort;

    public String openaiHost;
    public String openaiApiKey;
    public String openaiModel;
    public boolean openaiEnableProxy;
    public String openaiProxyHost;
    public String openaiProxyPort;


    @Override
    public void afterPropertiesSet() throws Exception {
        optionMapper.selectList(new QueryWrapper<Option>())
                .forEach(this::doSetting);
    }

    private void doSetting(Option option){
        switch (option.getKey()) {
            case "mailHost":
                mailHost = option.getValue();
                break;
            case "mailPort":
                mailPort = option.getValue();
                break;
            case "mailUsername":
                mailUsername = option.getValue();
                break;
            case "mailPassword":
                mailPassword = option.getValue();
                break;
            case "mailTo":
                mailTo = option.getValue();
                break;
            case "wxpusherAppToken":
                wxpusherAppToken = option.getValue();
                break;
            case "wxpusherUids":
                wxpusherUids = option.getValue();
                break;
            case "wxpusherTopicIds":
                wxpusherTopicIds = option.getValue();
                break;
            case "geminiApiKey":
                geminiApiKey = option.getValue();
                break;
            case "geminiHost":
                geminiHost = option.getValue();
                break;
            case "geminiModel":
                geminiModel = option.getValue();
                break;
            case "geminiEnableProxy":
                geminiEnableProxy = Boolean.parseBoolean(option.getValue());
                break;
            case "geminiProxyHost":
                geminiProxyHost = option.getValue();
                break;
            case "geminiProxyPort":
                geminiProxyPort = option.getValue();
                break;
            case "openaiHost":
                openaiHost = option.getValue();
                break;
            case "openaiApiKey":
                openaiApiKey = option.getValue();
                break;
            case "openaiModel":
                openaiModel = option.getValue();
                break;
            case "openaiEnableProxy":
                openaiEnableProxy = Boolean.parseBoolean(option.getValue());
                break;
            case "openaiProxyHost":
                openaiProxyHost = option.getValue();
                break;
            case "openaiProxyPort":
                openaiProxyPort = option.getValue();
                break;
        }

    }
}
