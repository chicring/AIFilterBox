package com.hjong.aifilterbox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjong.aifilterbox.common.BasePage;
import com.hjong.aifilterbox.common.ErrorCode;
import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.entity.Task;
import com.hjong.aifilterbox.exception.ServiceException;
import com.hjong.aifilterbox.mapper.SubtaskMapper;
import com.hjong.aifilterbox.mapper.TaskMapper;
import com.hjong.aifilterbox.schedule.CronTaskRegistrar;
import com.hjong.aifilterbox.schedule.SchedulingRunnable;
import com.hjong.aifilterbox.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    TaskMapper taskMapper;

    @Resource
    SubtaskMapper subtaskMapper;

    @Resource
    CronTaskRegistrar cronTaskRegistrar;


    @Override
    public void save(Task task) {
        if (taskMapper.insert(task) < 1){
            throw new ServiceException(ErrorCode.OPERATION_ERROR);
        }
    }

    @Override
    public void update(Task task) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Task::getId, task.getId());


        if (taskMapper.update(task, wrapper) < 1) {
            throw new ServiceException(ErrorCode.NOT_FOUND_ERROR);
        }

        this.updateEnable(task.getId(), task.isEnable());
    }

    @Override
    public void updateEnable(Long taskId, boolean enable) {
        LambdaUpdateWrapper<Task> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Task::getId, taskId);

        Task task = new Task();
        task.setEnable(enable);

        if (taskMapper.update(task, wrapper) < 1){
            throw new ServiceException(ErrorCode.NOT_FOUND_ERROR);
        }

        List<Subtask> subtasks = subtaskMapper.selectList(new LambdaQueryWrapper<Subtask>().eq(Subtask::getTaskId, taskId));

        subtasks.forEach(subtask -> {
            cronTaskRegistrar.removeTask(subtask.getId());
        });

        if(enable){
            subtasks.forEach(subtask -> {
                if (subtask.isEnable()){
                    cronTaskRegistrar.addTask(new SchedulingRunnable(subtask), subtask);
                }
            });
        }
    }


    @Override
    public void deleteById(Long id) {
        if (taskMapper.deleteById(id) > 0){
            List<Subtask> subtasks = subtaskMapper.selectList(new LambdaQueryWrapper<Subtask>().eq(Subtask::getTaskId, id));
            subtasks.forEach(subtask -> {
                cronTaskRegistrar.removeTask(subtask.getId());
            });
        } else {
            throw new ServiceException(ErrorCode.NOT_FOUND_ERROR);
        }
    }

    @Override
    public PageResp<Task> findPage(BasePage page){
        Page<Task> taskPage = taskMapper.selectPage(new Page<>(page.getPage(), page.getSize()), new QueryWrapper<>());

        return new PageResp<>(taskPage);
    }
}
