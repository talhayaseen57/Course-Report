package com.sherenterprise.coursereport.service;

import com.sherenterprise.coursereport.domain.MatchEntity;
import com.sherenterprise.coursereport.dto.MatchDto;
import com.sherenterprise.coursereport.dto.request.CourseReportApiResponseDto;
import com.sherenterprise.coursereport.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CourseReportService {

    private MatchRepository matchRepository;

    public CourseReportService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void pollCourseReportApi() {
        System.out.println("Polling Course Report API: " + LocalDateTime.now());

        RestTemplate restTemplate = new RestTemplate();

        LocalDate dateToday = LocalDate.now();
        LocalDate dateDayBeforeYesterday = dateToday.minusDays(2);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String startDateString = dateFormatter.format(dateToday);
        String endDateString = dateFormatter.format(dateDayBeforeYesterday);

        ResponseEntity<CourseReportApiResponseDto> apiResponse = restTemplate.getForEntity(
                "http://localhost:8080/get-mock-api?page=1&per_page=1000&date_start=" +
                        startDateString + "&date_end=" + endDateString, CourseReportApiResponseDto.class);

        CourseReportApiResponseDto responseData = apiResponse.getBody();

        if (responseData != null) {
            for (MatchDto apiMatch : responseData.matches()) {
                Optional<MatchEntity> dbMatchOpt = matchRepository.findByEmail(apiMatch.email());

                String messageFormat = """
                        Full Name: %s\s
                        Email Address: %s\s
                        Phone Number: %s""";
                final String message = String.format(messageFormat, apiMatch.fullName(), apiMatch.email(), apiMatch.phoneNumber());

                dbMatchOpt.ifPresentOrElse(dbMatch -> {
                    if (!dbMatch.getCreatedAt().equals(apiMatch.createdAt())) {
                        System.out.println("We should send message at slack: " + dbMatch);
                        dbMatch.setCreatedAt(apiMatch.createdAt());
                        matchRepository.save(dbMatch);

                        SlackBot.postMessage("Lead re-opted in: \n" + message);
                    }
                }, () -> {
                    System.out.println("The contact match is not present.");
                    MatchEntity newMatch = new MatchEntity(apiMatch.email(), apiMatch.phoneNumber(), apiMatch.fullName(), apiMatch.createdAt());
                    matchRepository.save(newMatch);

                    SlackBot.postMessage(message);
                });

            }
        }
    }

}
