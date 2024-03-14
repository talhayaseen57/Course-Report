package com.sherenterprise.coursereport.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MetaDataDto(
        @JsonProperty("pagination")
        PaginationDto paginationDto
) {
}
