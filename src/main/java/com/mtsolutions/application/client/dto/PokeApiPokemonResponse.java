package com.mtsolutions.application.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiPokemonResponse(
        Integer id,
        List<PokeApiTypesResponseDto> types,
        List<PokeApiStatsResponseDto> stats,
        List<PokeApiMovesResponseDto> moves,
        PokeApiSpritesResponseDto sprites,
        PokeApiCriesResponseDto cries) {
}