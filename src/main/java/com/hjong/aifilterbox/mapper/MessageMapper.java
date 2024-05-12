package com.hjong.aifilterbox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjong.aifilterbox.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
