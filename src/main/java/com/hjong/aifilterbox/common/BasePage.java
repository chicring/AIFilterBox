package com.hjong.aifilterbox.common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/
@Data
public class BasePage {

    private Integer page = 1;

    private Integer size = 10;
}
