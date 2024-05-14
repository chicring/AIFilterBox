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

    void send(Map<String, String> data);

default String buildHtmlContent(List<Message> messages) {
    StringBuilder sb = new StringBuilder();
    for (Message message : messages) {
        sb.append("<div style='position: relative; padding: 10px; margin-bottom: 10px;'>");
        if (message.getTitle() != null) {
            sb.append("<h3>").append(message.getTitle()).append("</h3>");
        }
        if (message.getTime() != null) {
            sb.append("<p style='opacity: 0.5;'>").append(message.getTime()).append("</p>");
        }

        if (message.getDescription() != null) {
            sb.append("<p>").append(message.getDescription()).append("</p>");
        }
        if (message.getPicUrl() != null) {
            sb.append("<img src='").append(message.getPicUrl()).append("'/>");
        }

        if (message.getTypeName() != null) {
            sb.append("<p>").append(message.getTypeName()).append("</p>");
        }

        sb.append("<div style='display: flex; justify-content: space-between; align-items: center;'>");

        sb.append("<div>");
        if (message.getOwnerPicUrl() != null) {
            sb.append("<img src='").append(message.getOwnerPicUrl()).append("' style='width: 50px; height: 50px;'/>");
        }
        if (message.getOwnerName() != null) {
            sb.append("<p style='margin-left: 10px; opacity: 0.5;'>").append(message.getOwnerName()).append("</p>");
        }
        sb.append("</div>");
        if (message.getPlatformName() != null) {
            sb.append("<p style='opacity: 0.5;'>").append(message.getPlatformName()).append("</p>");
        }
        sb.append("</div>");

        if (message.getUrl() != null) {
            sb.append("<a href='").append(message.getUrl()).append("' style='color: #007BFF;'>").append(message.getUrl()).append("</a>");
        }
        sb.append("</div>");
        sb.append("<hr>");
    }
    return sb.toString();
}
}

