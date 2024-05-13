package com.hjong.aifilterbox.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/

@Configuration
public class MailConfig {

    @Resource
    BeanConfig beanConfig;


    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(beanConfig.mailHost);
        mailSender.setPort(Integer.parseInt(beanConfig.mailPort));
        mailSender.setUsername(beanConfig.mailUsername);
        mailSender.setPassword(beanConfig.mailPassword);
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }
}
