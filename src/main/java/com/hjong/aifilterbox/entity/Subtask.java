package com.hjong.aifilterbox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/12
 **/

@EqualsAndHashCode(callSuper = true)
@TableName("ai_subtask")
@Data
public class Subtask extends BaseEntity{

    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务cron表达式
     */
    private String cronExpression;
    /**
     * 任务是否启用
     */
    private boolean enable;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 任务类型 默认default 后面可以扩展
     */
    private String type;
    /**
     * 请求头 json格式
     */
    private String requestHeader;
    /**
     * 关键词匹配
     */
    private String KeywordMatching;
    /**
     * 关键词过滤
     */
    private String keywordBlocking;
    /**
     * 请求体匹配规则 json格式
     */
    private String pattern;
    /**
     * 任务触发行为,  push | answer 所有的任务都会带有push行为，即使没有配置
     */
    private String action;
    /**
     * 发布地址域名，url需要拼接时使用
     */
    private String domain;

    /**
     * 消息id前缀，避免重复
     */
    private String prefix;
    /**
     * 主任务id
     */
    private Long taskId;
}
