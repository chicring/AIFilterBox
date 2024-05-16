package com.hjong.aifilterbox.controller;

import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.common.Result;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.entity.vo.SubtaskVO;
import com.hjong.aifilterbox.service.SubTaskService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/

@CrossOrigin
@RequestMapping("/subtask")
@RestController
public class SubTaskController {


    @Resource
    SubTaskService subTaskService;

    @GetMapping
    public Result<PageResp<Subtask>> findPage(@RequestParam(value = "page", defaultValue = "1",required = false) Integer page,
                                              @RequestParam(value = "size", defaultValue = "10", required = false ) Integer size,
                                              @RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "enable", required = false) Boolean enable,
                                              @RequestParam(value = "type", required = false) String type,
                                              @RequestParam(value = "action", required = false) String action,
                                              @RequestParam(value = "pushType", required = false) String pushType){
        SubtaskVO vo = new SubtaskVO();
        vo.setPage(page);
        vo.setSize(size);
        vo.setName(name);
        vo.setEnable(enable);
        vo.setType(type);
        vo.setAction(action);
        vo.setPushType(pushType);

        return Result.ok(subTaskService.findPage(vo));
    }

    @PostMapping
    public Result<Void> saveSubTask(@RequestBody Subtask subtask) {
        subTaskService.save(subtask);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        subTaskService.deleteById(id);
        return Result.ok();
    }

    @PutMapping
    public Result<Void> updateSubTask(@RequestBody Subtask subtask) {
        subTaskService.update(subtask);
        return Result.ok();
    }

    @PutMapping("/enable")
    public Result<Void> enableSubTask(@RequestParam Long id, @RequestParam Boolean enable) {
        subTaskService.updateEnable(id, enable);
        return Result.ok();
    }
}
