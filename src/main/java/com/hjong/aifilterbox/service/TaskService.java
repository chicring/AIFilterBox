package com.hjong.aifilterbox.service;

import com.hjong.aifilterbox.common.BasePage;
import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.entity.Task;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
public interface TaskService {

    void save(Task task);

    void update(Task task);

    void updateEnable(Long id, boolean enable);

    void deleteById(Long id);

    PageResp<Task> findPage(BasePage page);
}
