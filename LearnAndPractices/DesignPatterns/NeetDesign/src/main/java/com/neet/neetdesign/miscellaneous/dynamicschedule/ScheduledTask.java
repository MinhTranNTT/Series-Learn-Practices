package com.neet.neetdesign.miscellaneous.dynamicschedule;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

public final class ScheduledTask {
    public volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> scheduledFuture = this.future;
        if (Objects.nonNull(scheduledFuture)) {
            scheduledFuture.cancel(true);
        }
    }
}
