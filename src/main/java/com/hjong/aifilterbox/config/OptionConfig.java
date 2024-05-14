package com.hjong.aifilterbox.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.mapper.OptionMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/

@Data
@Configuration
public class OptionConfig{

    @Resource
    OptionMapper optionMapper;

    @Resource
    private ApplicationContext context;

    public String mailHost;
    public String mailPort = "587";
    public String mailUsername;
    public String mailPassword;
    public String mailTo;

    public String wxpusherAppToken;
    public String wxpusherUids;
    public String wxpusherTopicIds;

    public String aiChannel = "openai";

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



    @PostConstruct
    public void init() {
        this.optionMapper.selectList(new QueryWrapper<>())
                .forEach(this::doSetting);
    }

    public void refresh(){
        this.optionMapper.selectList(new QueryWrapper<>())
                .forEach(this::doSetting);
    }

    public void refresh(Option option){
        doSetting(option);
    }

    public void refresh(List<Option> options){
        options.forEach(this::doSetting);
    }

    private void doSetting(Option option){
        int emailChange = 0;

        switch (option.getKey()) {
            case "mailHost":
                mailHost = option.getValue();
                emailChange++;
                break;
            case "mailPort":
                mailPort = option.getValue();
                emailChange++;
                break;
            case "mailUsername":
                mailUsername = option.getValue();
                emailChange++;
                break;
            case "mailPassword":
                mailPassword = option.getValue();
                emailChange++;
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
            case "aiChannel":
                aiChannel = option.getValue();
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

        if(emailChange > 0){
            refreshMailSender();
        }
    }



    private void refreshMailSender(){
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) beanFactory;

        registry.destroySingleton("javaMailSender");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setDefaultEncoding("UTF-8");

        registry.registerSingleton("javaMailSender", mailSender);
    }
}
