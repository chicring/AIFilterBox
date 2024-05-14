package com.hjong.aifilterbox.push;

import com.hjong.aifilterbox.entity.Message;
import com.hjong.aifilterbox.entity.Option;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/
public interface Push {

    void send(String title, String content);

    default String buildHtmlContent(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            sb.append("<div>");
            sb.append("<h3>").append(message.getTitle()).append("</h3>");
            sb.append("<p>").append(message.getDescription()).append("</p>");
            sb.append("<img src='").append(message.getPicUrl()).append("'/>");
            sb.append("<p>").append(message.getOwnerName()).append("</p>");
            sb.append("<img src='").append(message.getOwnerPicUrl()).append("'/>");
            sb.append("<p>").append(message.getTypeName()).append("</p>");
            sb.append("<p>").append(message.getPlatformName()).append("</p>");
            sb.append("<p>").append(message.getTime()).append("</p>");
            sb.append("<a href='").append(message.getUrl()).append("'>").append(message.getUrl()).append("</a>");
            sb.append("</div>");
        }
        return sb.toString();
    }
}
