package com.hjong.aifilterbox.schedule;

import com.hjong.aifilterbox.action.ActionFactory;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.mapper.MessageMapper;
import com.hjong.aifilterbox.monitor.Monitor;
import com.hjong.aifilterbox.monitor.MonitorFactory;
import com.hjong.aifilterbox.util.BloomFilter;
import com.hjong.aifilterbox.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

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

    BloomFilter bloomFilter;

    public SchedulingRunnable(Subtask subtask) {
        this.subtask = subtask;
        this.monitorFactory = SpringContextUtils.getBean(MonitorFactory.class);
        this.actionFactory = SpringContextUtils.getBean(ActionFactory.class);
        this.messageMapper = SpringContextUtils.getBean(MessageMapper.class);
        this.bloomFilter = SpringContextUtils.getBean(BloomFilter.class);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        log.info("任务{}开始执行", subtask.getName());

        //获取监控器
        Monitor monitor = monitorFactory.getMonitor(subtask.getType());
        List<Message> messages = monitor.getNoticeList(subtask);

        //使用布隆过滤器判断messageId是否存在, 如果不存在则提取出来后面在执行插入然后执行对应的action
        List<Message> filterMessages = messages.stream().filter(message -> !bloomFilter.contains(message.getMessageId()))
                .peek(message -> bloomFilter.add(message.getMessageId()))
                .toList();

        filterMessages.forEach(message -> System.out.println(message.getTitle()));

        //执行action
        if (!filterMessages.isEmpty()){
            actionFactory.getAction(subtask.getAction()).doAction(filterMessages,subtask.getPushType(),subtask.getPrompt());
        }

        log.info("任务{}执行完成, 用时{}", subtask.getName(), System.currentTimeMillis() - start);
    }
}
