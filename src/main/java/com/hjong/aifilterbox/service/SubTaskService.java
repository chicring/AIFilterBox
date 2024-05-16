package com.hjong.aifilterbox.service;

import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.entity.vo.SubtaskVO;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/
public interface SubTaskService {

    void save(Subtask subtask);

    void update(Subtask subtask);

    void updateEnable(Long id, boolean enable);

    void deleteById(Long id);

    PageResp<Subtask> findPage(SubtaskVO vo);
}
