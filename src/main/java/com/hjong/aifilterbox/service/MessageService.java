package com.hjong.aifilterbox.service;

import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.entity.vo.MessageVO;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/
public interface MessageService {


    void deleteById(Long id);

    PageResp<Message> findPage(MessageVO vo);
}
