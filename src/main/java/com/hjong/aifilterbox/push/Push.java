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

}

