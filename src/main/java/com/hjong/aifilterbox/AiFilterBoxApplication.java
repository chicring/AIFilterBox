package com.hjong.aifilterbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AiFilterBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiFilterBoxApplication.class, args);
    }

}
