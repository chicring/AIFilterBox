package com.hjong.aifilterbox.controller;

import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.entity.Option;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/14
 **/
@RestController
public class OptionController {

    @Resource
    OptionConfig optionConfig;


    @GetMapping("/option")
    public void test(String username){
//        Option option = new Option();
//        option.setKey("mailTo");
//        option.setValue(username);
//
//        optionConfig.refresh(option);

        Option option1 = new Option();
        option1.setKey("mailTo");
        option1.setValue(username);

        optionConfig.refresh(option1);
    }
}
