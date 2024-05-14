package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.config.OptionConfig;
import jakarta.annotation.Resource;

import lombok.Data;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
@Data
@Component
public class EmailPush implements Push {

    @Resource
    OptionConfig optionConfig;

    @Resource
    private JavaMailSender mailSender;

    @Override
    public void send(String title, String content) {
        if (mailSender == null) {
            throw new RuntimeException("暂未配置邮箱信息");
        }

        MimeMessagePreparator message = createHtmlMessage(title, content, optionConfig.getMailTo());
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
