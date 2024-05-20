package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.util.SpringContextUtils;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
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
@Slf4j
@Component
public class EmailPush implements Push {

    @Resource
    OptionConfig optionConfig;

    @Override
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.mail", durable = "true"),
            exchange = @Exchange(name = "push.direct", type = ExchangeTypes.DIRECT),
            key = {"mail"}
    ))
    public void send(Map<String, String> data) {
        MimeMessagePreparator message = createHtmlMessage(data.get("title"), data.get("content"), optionConfig.getMailTo());

        SpringContextUtils.getBean(JavaMailSender.class).send(message);
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
