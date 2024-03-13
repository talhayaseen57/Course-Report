package com.sherenterprise.coursereport;

import com.sherenterprise.coursereport.service.CourseReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseReportApplication.class, args);

        CourseReportService courseReportService = new CourseReportService();
        courseReportService.pollCourseReportApi();
    }

}
