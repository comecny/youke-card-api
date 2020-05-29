package com.youke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {
    public static ConfigurableApplicationContext applicationContext;
    public static void main(String[] args) {
        Application.applicationContext = SpringApplication.run(Application.class, args);
    }
}
