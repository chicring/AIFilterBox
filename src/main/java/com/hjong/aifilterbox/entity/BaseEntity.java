package com.hjong.aifilterbox.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.Instant;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/


@Data
public class BaseEntity {
    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Instant createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Instant updateTime;
}
