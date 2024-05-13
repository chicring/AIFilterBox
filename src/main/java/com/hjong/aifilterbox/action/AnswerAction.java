package com.hjong.aifilterbox.action;

import com.hjong.aifilterbox.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/

@Component
public class AnswerAction implements Action {

    @Override
    public void doAction(List<Message> messages,String ...args) {
        System.out.println("AnswerAction doAction");
    }
}
