package com.hjong.aifilterbox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ai_option")
public class Option extends BaseEntity {
    String key;
    String value;
}
