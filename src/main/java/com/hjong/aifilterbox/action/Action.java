package com.hjong.aifilterbox.action;

import com.hjong.aifilterbox.entity.Message;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
public interface Action {

    void doAction(List<Message> messages);
}
