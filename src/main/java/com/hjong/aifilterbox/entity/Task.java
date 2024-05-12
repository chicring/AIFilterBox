package com.hjong.aifilterbox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/10
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ai_task")
public class Task extends BaseEntity{

    private String name;
    private String description;
    private boolean enable;
}
