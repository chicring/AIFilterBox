package com.hjong.aifilterbox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.mapper.OptionMapper;
import com.hjong.aifilterbox.service.OptionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper,Option> implements OptionService{

    @Resource
    OptionMapper optionMapper;

    @Resource
    OptionConfig optionConfig;

    @Override
    public List<Option> selectList() {
        return optionMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void saveOption(Option option) {
        this.saveOrUpdate(option);
        optionConfig.refresh(option);
    }

    @Override
    public void saveBatch(List<Option> options) {
        this.saveOrUpdateBatch(options);
        optionConfig.refresh(options);
    }

    @Override
    public void update(Option option) {
        optionMapper.updateById(option);
        optionConfig.refresh(option);
    }
}
