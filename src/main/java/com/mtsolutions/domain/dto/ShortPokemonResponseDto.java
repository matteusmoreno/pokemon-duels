package com.mtsolutions.domain.dto;

import com.mtsolutions.domain.entity.Pokemon;
import com.mtsolutions.domain.model.PokemonStat;
import com.mtsolutions.domain.model.PokemonType;

import java.util.List;

public record ShortPokemonResponseDto(
        Integer pokemonId,
        String name,
        String profileImageUrl,
        List<PokemonType> types,
        List<PokemonStat> stats
) {
    public ShortPokemonResponseDto(Pokemon pokemon) {
        this(
                pokemon.getPokemonId(),
                pokemon.getName(),
                pokemon.getProfileImageUrl(),
                pokemon.getTypes(),
                pokemon.getStats()
        );
    }
}
