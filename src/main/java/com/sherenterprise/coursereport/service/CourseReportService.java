package com.sherenterprise.coursereport.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CourseReportService {

    public void pollCourseReportApi () {
        System.out.println("Polling Course Report API: " + LocalDateTime.now());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> apiResponse = restTemplate.getForEntity("http://localhost:8080/get-mock-api", String.class);

        System.out.println(apiResponse);
    }
}
