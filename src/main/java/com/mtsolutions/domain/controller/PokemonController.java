package com.mtsolutions.domain.controller;

import com.mtsolutions.domain.entity.Pokemon;
import com.mtsolutions.domain.service.PokemonService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public Pokemon createPokemon(String name) {
        return this.pokemonService.createPokemon(name);
    }

    public Pokemon findPokemonByName(String name) {
        return this.pokemonService.findPokemonByName(name);
    }
}
