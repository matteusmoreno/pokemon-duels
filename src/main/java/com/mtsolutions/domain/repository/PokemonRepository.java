package com.mtsolutions.domain.repository;

import com.mtsolutions.application.exception.PokemonNotFoundException;
import com.mtsolutions.domain.entity.Pokemon;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PokemonRepository implements PanacheMongoRepositoryBase<Pokemon, String> {

    public Boolean existsByName(String name) {
        return find("name", name.toLowerCase())
                .firstResultOptional()
                .isPresent();
    }

    public Pokemon findByName(String name) {
        return (find("name", name.toLowerCase())
                .firstResultOptional()
                .orElseThrow(PokemonNotFoundException::new));
    }
}
