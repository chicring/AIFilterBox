package com.hjong.aifilterbox.service;

import com.hjong.aifilterbox.entity.Option;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/

public interface OptionService {

    List<Option> selectList();

    void save(Option option);

    void update(Option option);
}
