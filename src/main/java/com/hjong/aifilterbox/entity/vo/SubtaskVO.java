package com.hjong.aifilterbox.entity.vo;

import com.hjong.aifilterbox.common.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SubtaskVO extends BasePage {

    private String name;

    private Boolean enable;

    private String type;

    private String action;

    private String pushType;
}
