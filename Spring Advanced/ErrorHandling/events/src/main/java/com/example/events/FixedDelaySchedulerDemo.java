package com.example.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixedDelaySchedulerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronSchedulerDemo.class);

    @Scheduled(fixedDelay = 20000, initialDelay = 10000)
    public void showTimeWithFixedDelay(){
        LOGGER.info("Hello, from fixed delay scheduler at {}", LocalDateTime.now());
    }
}
