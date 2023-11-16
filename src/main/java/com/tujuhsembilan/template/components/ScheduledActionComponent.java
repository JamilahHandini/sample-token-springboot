package com.tujuhsembilan.template.components;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class is an example of how to use Spring Scheduler
 */
@Component
@Slf4j
public class ScheduledActionComponent {

    // To see more example of cron expression used in Spring Scheduler
    // See https://stackoverflow.com/a/26147143
    @Scheduled(cron = "0 20 4 * * *") // Every day at 04:20:00
    public void cronJobExample() {
        log.info("Cron job triggered exactly at {}", new LocalDateTime());
    }
}
