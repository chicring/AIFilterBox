package com.hjong.aifilterbox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjong.aifilterbox.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询近24小时内的消息原始id
     * @return 消息原始id list
     */
    @Select("SELECT message_id FROM ai_message WHERE create_time > datetime('now', '-1 day')")
    List<String> selectMessageIdList();
}
