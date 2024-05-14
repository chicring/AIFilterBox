package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.config.OptionConfig;
import jakarta.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

@Component
public class EmailPush implements Push {

    @Resource
    OptionConfig optionConfig;

    @Resource
    private JavaMailSender mailSender;

    @Override
    @RabbitHandler
    @RabbitListener(queues = "mail")
    public void send(Map<String, String> data) {

        MimeMessagePreparator message = createHtmlMessage(data.get("title"), data.get("content"), optionConfig.getMailTo());
        mailSender.send(message);
    }

    private MimeMessagePreparator createHtmlMessage(String title, String htmlContent, String email) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setSubject(title);
            messageHelper.setTo(email);
            messageHelper.setFrom(optionConfig.getMailUsername());
            messageHelper.setText(htmlContent, true);
        };
    }

}
