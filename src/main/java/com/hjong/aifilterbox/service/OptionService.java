package com.hjong.aifilterbox.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjong.aifilterbox.entity.Option;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/

public interface OptionService extends IService<Option> {

    List<Option> selectList();

    void saveOption(Option option);

    void saveBatch(List<Option> options);

    void update(Option option);
}
