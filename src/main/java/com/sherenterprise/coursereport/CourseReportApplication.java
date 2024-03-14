package com.sherenterprise.coursereport;

import com.sherenterprise.coursereport.service.CourseReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CourseReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseReportApplication.class, args);
    }

}
