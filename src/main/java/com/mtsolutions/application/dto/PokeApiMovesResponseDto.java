package com.mtsolutions.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiMovesResponseDto(
        Move move
) {
    public record Move(
        String name
    ){}
}
