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

    void modifySetting(List<Option> options);

    void removeSetting();
}
