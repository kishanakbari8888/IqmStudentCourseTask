package com.example.StudentCourse.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule{


    @Scheduled(cron = "0/5 * * * * *") // Cron expression for running every minute
    public void execute() {
        System.out.println("cron jobs running...");
    }
}