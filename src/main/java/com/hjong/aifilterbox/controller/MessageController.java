package com.hjong.aifilterbox.controller;

import com.hjong.aifilterbox.common.Result;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.common.PageResp;
import com.hjong.aifilterbox.entity.vo.MessageVO;
import com.hjong.aifilterbox.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/15
 **/
@RequestMapping("/message")
@RestController
public class MessageController {

    @Resource
    MessageService messageService;

    @GetMapping
    public Result<PageResp<Message>> findPage(@RequestParam(value = "page", defaultValue = "1",required = false) Integer page,
                                              @RequestParam(value = "size", defaultValue = "10", required = false ) Integer size,
                                              @RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "ownerName", required = false) String ownerName,
                                              @RequestParam(value = "typeName", required = false) String typeName,
                                              @RequestParam(value = "platformName", required = false) String platformName,
                                              @RequestParam(value = "startTime", required = false) Instant startTime,
                                              @RequestParam(value = "endTime", required = false) Instant endTime,
                                              @RequestParam(value = "used",required = false) boolean used
    ) {
        MessageVO vo = new MessageVO();
        vo.setPage(page);
        vo.setSize(size);
        vo.setTitle(title);
        vo.setOwnerName(ownerName);
        vo.setTypeName(typeName);
        vo.setPlatformName(platformName);
        vo.setStartTime(startTime);
        vo.setEndTime(endTime);
        vo.setUsed(used);

        return Result.ok(messageService.findPage(vo));
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        messageService.deleteById(id);
    }


}
