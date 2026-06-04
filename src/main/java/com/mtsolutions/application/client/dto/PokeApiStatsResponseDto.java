package com.mtsolutions.application.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiStatsResponseDto(
        @JsonProperty("base_stat")
        Integer baseStat,
        Stat stat

) {

    public record Stat(String name) {}

}
