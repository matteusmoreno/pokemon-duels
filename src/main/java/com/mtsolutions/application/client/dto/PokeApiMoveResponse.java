package com.mtsolutions.application.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiMoveResponse(
        Integer id,
        String name,
        Integer accuracy,
        Integer power,
        Integer pp
) {}