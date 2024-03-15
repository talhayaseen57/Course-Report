package com.sherenterprise.coursereport.service;

import com.sherenterprise.coursereport.domain.MatchEntity;
import com.sherenterprise.coursereport.dto.MatchDto;
import com.sherenterprise.coursereport.dto.request.CourseReportApiResponseDto;
import com.sherenterprise.coursereport.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseReportService {

    private MatchRepository matchRepository;

    public CourseReportService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void pollCourseReportApi () {
        System.out.println("Polling Course Report API: " + LocalDateTime.now());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CourseReportApiResponseDto> apiResponse = restTemplate.getForEntity("http://localhost:8080/get-mock-api", CourseReportApiResponseDto.class);

        CourseReportApiResponseDto responseData = apiResponse.getBody();

        if (responseData != null) {
            for (MatchDto match : responseData.matches()) {
                Optional<MatchEntity> aMatchOpt = matchRepository.findByEmail(match.email());

                aMatchOpt.ifPresentOrElse(aMatch -> {
                    System.out.println("The contact match is present: " + aMatch);
                }, () -> {
                    System.out.println("The contact match is not present.");
                    MatchEntity newMatch = new MatchEntity(match.email(), match.phoneNumber(), match.fullName(), match.creationDate());
                    matchRepository.save(newMatch);
                });

            }
        }
    }

}
