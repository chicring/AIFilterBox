package com.hjong.aifilterbox.controller;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/

import com.hjong.aifilterbox.common.BasePage;
import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.common.Result;
import com.hjong.aifilterbox.entity.Task;
import com.hjong.aifilterbox.service.TaskService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/task")
@RestController
public class TaskController {

    @Resource
    TaskService taskService;

    @GetMapping
    public Result<PageResp<Task>> findPage(@RequestParam(value = "page", defaultValue = "1",required = false) Integer page,
                                           @RequestParam(value = "size", defaultValue = "10", required = false ) Integer size){
        BasePage vo = new BasePage();
        vo.setPage(page);
        vo.setSize(size);

        return Result.ok(taskService.findPage(vo));
    }

    @PostMapping
    public Result<Void> saveTask(@RequestParam Task task) {
        taskService.save(task);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return Result.ok();
    }

    @PutMapping
    public Result<Void> updateTask(@RequestParam Task task) {
        taskService.update(task);
        return Result.ok();
    }

    @PutMapping("/enable")
    public Result<Void> updateEnable(@RequestParam Long id, @RequestParam boolean enable) {
        taskService.updateEnable(id, enable);
        return Result.ok();
    }
}
