package com.mtsolutions.application.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiTypesResponseDto(
        Type type
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Type(String name) {}
}
