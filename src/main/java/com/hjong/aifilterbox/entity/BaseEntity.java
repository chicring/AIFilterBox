package com.hjong.aifilterbox.entity;

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
    private Instant createTime;
    private Instant updateTime;
}
