package com.mtsolutions.application.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiSpritesResponseDto(
        Other other
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Other(
            @JsonProperty("official-artwork")
            OfficialArtwork officialArtwork,
            @JsonProperty("showdown")
            Showdown showdown
    ){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record OfficialArtwork(
            @JsonProperty("front_default")
            String frontDefault
    ){}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Showdown(
            @JsonProperty("front_default")
            String frontDefault,
            @JsonProperty("back_default")
            String backDefault
    ){}
}