package com.hjong.aifilterbox.action;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import static com.hjong.aifilterbox.action.Constant.ANSWER_ACTION;
import static com.hjong.aifilterbox.action.Constant.PUSH_ACTION;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/

@Component
public class ActionFactory {

        @Resource
        PushAction pushAction;

        @Resource
        AnswerAction answerAction;

        public Action getAction(String type) {

            return switch (type) {
                case PUSH_ACTION -> pushAction;
                case ANSWER_ACTION -> answerAction;
                default -> null;
            };
        }
}
