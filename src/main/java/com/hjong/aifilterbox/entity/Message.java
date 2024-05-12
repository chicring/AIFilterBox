package com.hjong.aifilterbox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ai_message")
public class Message extends BaseEntity{

    /**
     * 消息原始id
     */
    private String messageId;
    /**
     * 消息标题 或者 消息主体
     */
    private String title;
    /**
     * 消息描述 可以为空
     */
    private String description;
    /**
     * 消息图片地址 可以为空
     */
    private String picUrl;
    /**
     * 消息发布者名称
     */
    private String ownerName;
    /**
     * 消息发布者头像地址
     */
    private String ownerPicUrl;
    /**
     * 消息类型
     */
    private String typeName;
    /**
     * 消息平台
     */
    private String platformName;
    /**
     * 消息发布时间
     */
    private String time;

    /**
     * 消息地址url
     */
    private String url;
    /**
     * 消息是否已使用
     */
    private boolean used;
}
