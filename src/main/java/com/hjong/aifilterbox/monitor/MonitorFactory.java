package com.hjong.aifilterbox.monitor;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import static com.hjong.aifilterbox.monitor.Constant.*;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/9
 **/

@Component
public class MonitorFactory {

    @Resource
    DefaultMonitor defaultMonitor;


    public Monitor getMonitor(String type) {

        return switch (type) {
            case DEFAULT_MONITOR -> defaultMonitor;
            default -> null;
        };
    }
}
