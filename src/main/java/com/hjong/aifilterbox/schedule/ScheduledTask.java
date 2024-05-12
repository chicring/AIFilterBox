package com.hjong.aifilterbox.schedule;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/11
 **/
public final class ScheduledTask {

    public volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> scheduledFuture = this.future;
        if (Objects.nonNull(scheduledFuture)) {
            scheduledFuture.cancel(true);
        }
    }
}
