package com.sherenterprise.coursereport.cronjob;

import com.sherenterprise.coursereport.service.CourseReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CourseReportScheduler {

    private CourseReportService courseReportService;

    public CourseReportScheduler(CourseReportService courseReportService) {
        this.courseReportService = courseReportService;
    }

    @Scheduled(fixedRate = 5000)
    public void pollCourseReportApi() {
        courseReportService.pollCourseReportApi();
    }

}
