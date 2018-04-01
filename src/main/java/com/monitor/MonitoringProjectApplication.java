package com.monitor;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class MonitoringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringProjectApplication.class, args);
    }


    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
