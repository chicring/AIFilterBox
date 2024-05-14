package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
public interface Push {

    void send(Map<String, Object> data);

    default String buildHtmlContent(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            sb.append("<div style='position: relative;'>");
            if (message.getTitle() != null) {
                sb.append("<h3>").append(message.getTitle()).append("</h3>");
            }
            if (message.getDescription() != null) {
                sb.append("<p>").append(message.getDescription()).append("</p>");
            }
            if (message.getPicUrl() != null) {
                sb.append("<img src='").append(message.getPicUrl()).append("'/>");
            }
            if (message.getOwnerName() != null) {
                sb.append("<p>").append(message.getOwnerName()).append("</p>");
            }
            if (message.getOwnerPicUrl() != null) {
                sb.append("<img src='").append(message.getOwnerPicUrl()).append("' style='position: absolute; bottom: 0; left: 0;'/>");
            }
            if (message.getTypeName() != null) {
                sb.append("<p>").append(message.getTypeName()).append("</p>");
            }
            if (message.getPlatformName() != null) {
                sb.append("<p>").append(message.getPlatformName()).append("</p>");
            }
            if (message.getTime() != null) {
                sb.append("<p style='position: absolute; bottom: 0; right: 0;'>").append(message.getTime()).append("</p>");
            }
            if (message.getUrl() != null) {
                sb.append("<a href='").append(message.getUrl()).append("'>").append(message.getUrl()).append("</a>");
            }
            sb.append("</div>");
            sb.append("<hr/>");
        }
        return sb.toString();
    }
}
