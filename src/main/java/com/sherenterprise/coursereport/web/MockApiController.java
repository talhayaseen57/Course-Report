package com.sherenterprise.coursereport.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {
    @GetMapping("/get-mock-api")
    public ResponseEntity<String> fetchData () {

        String matches = "{\"matches\":[{\"full_name\":\"John Doe\",\"phone_number\":\"+1 555-0100\",\"created_at\":\"February 4, 2024 at 9:19am UTC\",\"email\":\"john.doe@example.com\",\"experience\":\"Beginner\",\"availability\":\"Part Time\",\"location\":\"Online\",\"online\":true,\"track\":\"Full Stack Developer\"},{\"full_name\":\"Jane Smith\",\"phone_number\":\"+1 555-0101\",\"created_at\":\"February 4, 2024 at 7:33am UTC\",\"email\":\"jane.smith@example.com\",\"experience\":\"Beginner\",\"availability\":\"Part Time\",\"location\":\"Online\",\"online\":true,\"track\":\"Full Stack Developer\"},{\"full_name\":\"Alex Johnson\",\"phone_number\":\"+1 555-0102\",\"created_at\":\"February 4, 2024 at 2:49am UTC\",\"email\":\"alex.johnson@example.com\",\"experience\":\"Beginner\",\"availability\":\"Part Time\",\"location\":\"Online\",\"online\":true,\"track\":\"Full Stack Developer\"},{\"full_name\":\"Emily Davis\",\"phone_number\":\"+1 555-0103\",\"created_at\":\"February 3, 2024 at 10:24pm UTC\",\"email\":\"emily.davis@example.com\",\"experience\":\"Some Experience\",\"availability\":\"Full Time\",\"location\":\"Online\",\"online\":true,\"track\":\"Full Stack Developer\"}],\"meta\":{\"pagination\":{\"current_page\":\"1\",\"total_objects\":4,\"total_pages\":1,\"per_page\":\"25\"}}}";

        return ResponseEntity.ok(matches);
    }

}
