package com.hjong.aifilterbox.monitor;

import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Subtask;

import java.util.List;

/**
 * 监控器
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

public interface Monitor {

    List<Message> getNoticeList(Subtask subtask);
}
