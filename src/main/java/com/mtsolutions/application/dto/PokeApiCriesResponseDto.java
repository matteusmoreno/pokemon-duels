package com.mtsolutions.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiCriesResponseDto(
        String latest,
        String legacy
) {
}
