package com.hjong.aifilterbox.controller;

import com.hjong.aifilterbox.common.Result;
import com.hjong.aifilterbox.config.OptionConfig;
import com.hjong.aifilterbox.entity.Option;
import com.hjong.aifilterbox.service.OptionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/14
 **/


@RequestMapping("/option")
@RestController
public class OptionController {

    @Resource
    OptionService optionService;

    @GetMapping
    public Result<List<Option>> findAll() {
        return Result.ok(optionService.selectList());
    }

    @PostMapping
    public Result<Void> save(@RequestBody Option option) {
        optionService.saveOption(option);
        return Result.ok();
    }

    @PostMapping("/batch")
    public Result<Void> saveBatch(@RequestBody List<Option> options) {
        optionService.saveBatch(options);
        return Result.ok();
    }

    @PutMapping
    public Result<Void> update(Option option) {
        optionService.update(option);
        return Result.ok();
    }


}
