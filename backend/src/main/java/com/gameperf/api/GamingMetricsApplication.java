package com.gameperf.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class GamingMetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingMetricsApplication.class, args);
    }
}
