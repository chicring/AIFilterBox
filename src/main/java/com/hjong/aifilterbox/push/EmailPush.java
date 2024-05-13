package com.hjong.aifilterbox.push;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.mapper.OptionMapper;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hjong.aifilterbox.entity.Constant.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
@Data
@Component
public class EmailPush implements Push, InitializingBean {

    @Resource
    OptionMapper optionMapper;

    private JavaMailSenderImpl mailSender;


    @Override
    public void afterPropertiesSet() throws Exception {
        List<Option> options = optionMapper.selectList(new QueryWrapper<Option>().eq("type", Mail_TYPE));
        if (options.isEmpty()) {
            return;
        }
        mailSender = new JavaMailSenderImpl();

        doSetting(options);
    }

    @Override
    public void send(Message message) {
        if (mailSender == null) {
            throw new RuntimeException("暂未配置邮箱信息");
        }
    }

    @Override
    public void modifySetting(List<Option> options) {
        doSetting(options);
    }

    @Override
    public void removeSetting() {
        mailSender = null;
    }

    private void doSetting(List<Option> options) {
        options.forEach(option -> {
            switch (option.getKey()) {
                case Mail_HOST:
                    mailSender.setHost(option.getValue());
                    break;
                case Mail_PORT:
                    mailSender.setPort(Integer.parseInt(option.getValue()));
                    break;
                case Mail_FROM:
                    mailSender.setUsername(option.getValue());
                    break;
                case Mail_PASSWORD:
                    mailSender.setPassword(option.getValue());
                    break;
            }
        });
    }
}
