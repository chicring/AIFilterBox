package com.hjong.aifilterbox.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.entity.Task;
import com.hjong.aifilterbox.mapper.SubtaskMapper;
import com.hjong.aifilterbox.mapper.TaskMapper;
import com.hjong.aifilterbox.monitor.MonitorFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
@Slf4j
@Component
public class ScheduleRunner implements CommandLineRunner {

    @Resource
    SubtaskMapper subtaskMapper;

    @Resource
    CronTaskRegistrar cronTaskRegistrar;


    @Override
    public void run(String... args) throws Exception {
        log.info("开始初始化定时任务");
        QueryWrapper<Subtask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable", true);
        List<Subtask> subtasks =  subtaskMapper.selectList(queryWrapper);
        subtasks.forEach( subtask -> {
            cronTaskRegistrar.addTask(new SchedulingRunnable(subtask), subtask);
        });
        log.info("定时任务初始化完成,共{}个任务", subtasks.size());
    }
}
