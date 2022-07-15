package ru.newsystems.nispro_bot.webservice.task;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.ScheduledFuture;

@Data
@Builder
public class SendDataDTO {
    private ScheduledFuture<?> schedule;
    private SendOperationTask task;

    public void stopSchedule() {
        schedule.cancel(false);
    }
}
