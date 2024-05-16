package com.hjong.aifilterbox.entity.vo;

import com.hjong.aifilterbox.common.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageVO extends BasePage {

    private String title;
    private String ownerName;
    private String typeName;
    private String platformName;
    private Instant startTime;
    private Instant endTime;
}
