package com.hjong.aifilterbox.schedule;

import com.hjong.aifilterbox.entity.Subtask;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
@Component
public class CronTaskRegistrar implements DisposableBean {

    @Resource
    TaskScheduler taskScheduler;

    private final Map<Long,ScheduledTask> scheduledTaskMap = new ConcurrentHashMap<>(64);

    public void addTask(Runnable runnable, Subtask task){
        addTask(new CronTask(runnable, task.getCronExpression()), task.getId());
    }

    public void addTask(CronTask cronTask, Long taskId) {
        if (Objects.nonNull(cronTask)) {
            if (this.scheduledTaskMap.containsKey(taskId)) {
                removeTask(taskId);
            }
            this.scheduledTaskMap.put(taskId, scheduleTask(cronTask));
        }
    }

    public void removeTask(Long taskId) {
        ScheduledTask scheduledTask = this.scheduledTaskMap.remove(taskId);
        if (Objects.nonNull(scheduledTask)) {
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }


    @Override
    public void destroy(){
        this.scheduledTaskMap.values().forEach(ScheduledTask::cancel);
        this.scheduledTaskMap.clear();
    }
}
