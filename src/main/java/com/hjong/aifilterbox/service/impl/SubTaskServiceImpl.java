package com.hjong.aifilterbox.service.impl;

import com.hjong.aifilterbox.common.ErrorCode;
import com.hjong.aifilterbox.entity.Page;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.exception.ServiceException;
import com.hjong.aifilterbox.mapper.SubtaskMapper;
import com.hjong.aifilterbox.schedule.CronTaskRegistrar;
import com.hjong.aifilterbox.schedule.SchedulingRunnable;
import com.hjong.aifilterbox.service.SubTaskService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/

@Service
public class SubTaskServiceImpl implements SubTaskService {

    @Resource
    CronTaskRegistrar cronTaskRegistrar;

    @Resource
    SubtaskMapper subtaskMapper;

    @Override
    public void save(Subtask subtask) {
        if (subtaskMapper.insert(subtask) < 1){
            throw new ServiceException(ErrorCode.OPERATION_ERROR);
        }else {
            cronTaskRegistrar.addTask(new SchedulingRunnable(subtask), subtask);
        }
    }

    @Override
    public void update(Subtask subtask) {
        if (subtaskMapper.updateById(subtask) < 1) {
            throw new ServiceException(ErrorCode.NOT_FOUND_ERROR);
        }

        cronTaskRegistrar.removeTask(subtask.getTaskId());

        if (subtask.isEnable()) {
            cronTaskRegistrar.addTask(new SchedulingRunnable(subtask), subtask);
        }
    }

    @Override
    public void updateEnable(Long id, boolean enable) {

        cronTaskRegistrar.removeTask(id);

        if (enable){
            Subtask subtask = subtaskMapper.selectById(id);
            if (subtask == null){
                throw new ServiceException(ErrorCode.NOT_FOUND_ERROR);
            }
            cronTaskRegistrar.addTask(new SchedulingRunnable(subtask), subtask);
        }
    }

    @Override
    public void deleteById(Long id) {
        cronTaskRegistrar.removeTask(id);

        if (subtaskMapper.deleteById(id) < 1){
            throw new ServiceException(ErrorCode.NOT_FOUND_ERROR);
        }
    }

    @Override
    public Page<Subtask> findPage() {
        return null;
    }
}
