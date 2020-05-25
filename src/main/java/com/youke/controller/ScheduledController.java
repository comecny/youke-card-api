package com.youke.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ScheduledController {

    static Logger logger = LogManager.getLogger(ScheduledController.class.getName());

    //@Scheduled(cron = "0/5 * * * * ?")
    public void scheduledTasks() throws ParseException {
        logger.info("----------------定时任务打印------------------------");
    }
}
