package com.hjong.aifilterbox.monitor;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Subtask;
import com.hjong.aifilterbox.util.JsonUtil;
import com.jayway.jsonpath.JsonPath;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/10
 **/

@Component
public class DefaultMonitor implements Monitor {

    @Resource
    RestClient restClient;

    @Override
    public List<Message> getNoticeList(Subtask subtask) {

        //设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();

        if (!subtask.getRequestHeader().isEmpty()) {
            Map<String, String> customHeaders = JsonUtil.parseObject(subtask.getRequestHeader(), HashMap.class);
            for (Map.Entry<String, String> entry : customHeaders.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }

        String json =  restClient.get()
                .uri(subtask.getUrl())
                .headers(httpHeaders1 -> httpHeaders1.addAll(httpHeaders))
                .retrieve()
                .body(String.class);

        //提取使用jsonpath 提取json中的数据

        List<Message> messages = new ArrayList<>();


        Map<String, String> pattern = JsonUtil.parseObject(subtask.getPattern(), HashMap.class);


        List<Map<String,String>> items = JsonPath.read(json, pattern.get("root"));

        BeanDesc desc = BeanUtil.getBeanDesc(Message.class);

        for (Map<String, String> item : items) {
            Message message = new Message();
            for (Map.Entry<String, String> entry : pattern.entrySet()) {

                switch (entry.getKey()) {
                    case "root" -> {
                        continue;
                    }
                    case "messageId" -> {
                        message.setMessageId(subtask.getPrefix() + "-" + JsonPath.read(item, entry.getValue()).toString());
                        continue;
                    }
                    case "url" -> {
                        if (subtask.getDomain() != null) {
                            message.setUrl(subtask.getDomain() + JsonPath.read(item, entry.getValue()).toString());
                            continue;
                        }
                        message.setUrl(JsonPath.read(item, entry.getValue()).toString());
                        continue;
                    }
                }

                String fieldName = entry.getKey();
                String jsonPathExpression = entry.getValue();

                Object fieldValue = JsonPath.read(item, jsonPathExpression);

                desc.getProp(fieldName).setValue(message, fieldValue);
            }
            messages.add(message);
        }


        return messages;
    }
}
