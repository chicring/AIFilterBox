package com.hjong.aifilterbox.schedule;

import com.hjong.aifilterbox.action.ActionFactory;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.entity.Task;
import com.hjong.aifilterbox.mapper.MessageMapper;
import com.hjong.aifilterbox.monitor.Monitor;
import com.hjong.aifilterbox.monitor.MonitorFactory;
import com.hjong.aifilterbox.util.SpringContextUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/10
 **/

@Slf4j
public class SchedulingRunnable implements Runnable{

    private final Subtask subtask;

    MonitorFactory monitorFactory;

    ActionFactory actionFactory;

    MessageMapper messageMapper;


    public SchedulingRunnable(Subtask subtask) {
        this.subtask = subtask;
        this.monitorFactory = SpringContextUtils.getBean(MonitorFactory.class);
        this.actionFactory = SpringContextUtils.getBean(ActionFactory.class);
        this.messageMapper = SpringContextUtils.getBean(MessageMapper.class);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        log.info("任务{}开始执行", subtask.getName());

        //获取监控器
        Monitor monitor = monitorFactory.getMonitor(subtask.getType());
        List<Message> messages = monitor.getNoticeList(subtask);

        //在数据库中查找是否存在messageId, 如果不存在则提取出来后面在执行插入然后执行对应的action



        //执行action
        actionFactory.getAction(subtask.getAction()).doAction(messages);

        log.info("任务{}执行完成, 用时{}", subtask.getName(), System.currentTimeMillis() - start);
    }
}
