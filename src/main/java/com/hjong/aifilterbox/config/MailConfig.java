package com.hjong.aifilterbox.config;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/

@Configuration
public class MailConfig {

    @Resource
    OptionConfig optionConfig;


    @Bean
    @Primary
    public JavaMailSender javaMailSender(){

//        if(optionConfig.mailHost == null || optionConfig.mailUsername == null || optionConfig.mailPassword == null){
//            return null;
//        }
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(optionConfig.mailHost);
        mailSender.setPort(Integer.parseInt(optionConfig.mailPort));
        mailSender.setUsername(optionConfig.mailUsername);
        mailSender.setPassword(optionConfig.mailPassword);
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }
}
