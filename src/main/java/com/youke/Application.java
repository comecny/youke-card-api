package com.youke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {
    public static ConfigurableApplicationContext applicationContext;
    private static final Logger logger = LogManager.getLogger(Application.class.getName());
    public static void main(String[] args) {
        Application.applicationContext = SpringApplication.run(Application.class, args);
        logger.info("Server Application Context : " + applicationContext);
    }
}
