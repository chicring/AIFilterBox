package com.hjong.aifilterbox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.entity.vo.MessageVO;
import com.hjong.aifilterbox.mapper.MessageMapper;
import com.hjong.aifilterbox.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageMapper messageMapper;

    @Override
    public void deleteById(Long id) {
        messageMapper.deleteById(id);
    }

    @Override
    public PageResp<Message> findPage(MessageVO vo) {

        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(vo.getTitle() != null, "title", vo.getTitle())
                .like(vo.getOwnerName() != null, "owner_name", vo.getOwnerName())
                .like(vo.getTypeName() != null, "type_name", vo.getTypeName())
                .like(vo.getPlatformName() != null, "platform_name", vo.getPlatformName())
                .eq(vo.getUsed() != null, "used", vo.getUsed())
                .ge(vo.getStartTime() != null, "create_time", vo.getStartTime())
                .le(vo.getEndTime() != null, "create_time", vo.getEndTime())
                .orderByDesc("create_time");
        Page<Message> page = new Page<>(vo.getPage(), vo.getSize());

        page = messageMapper.selectPage(page, queryWrapper);

        return new PageResp<>(page);
    }
}
