package com.sherenterprise.coursereport.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sherenterprise.coursereport.dto.MatchDto;
import com.sherenterprise.coursereport.dto.MetaDataDto;

import java.util.List;

public record CourseReportApiResponseDto(
        List<MatchDto> matches,
        @JsonProperty("meta")
        MetaDataDto metaDataDto
) {
}
