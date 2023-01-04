package com.example.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Component
public class CronSchedulerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronSchedulerDemo.class);

    @Scheduled(cron = "${schedulers.cron}")
    public void showTimeWithCron(){
        LOGGER.info("Hello, from cron scheduler at {}", LocalDateTime.now());
    }
}
