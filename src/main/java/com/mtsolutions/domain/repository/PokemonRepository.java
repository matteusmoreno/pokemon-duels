package com.mtsolutions.domain.repository;

import com.mtsolutions.application.exception.PokemonNotFoundException;
import com.mtsolutions.domain.entity.Pokemon;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PokemonRepository implements PanacheMongoRepositoryBase<Pokemon, String> {

    public Pokemon findByPokemonId(Integer pokemonId) {
        return find("pokemonId", pokemonId)
                .firstResultOptional()
                .orElseThrow(PokemonNotFoundException::new);
    }

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

    public List<Pokemon> findAllSortedByPokemonId(int page, int size) {
        return findAll().page(page, size).list()
                .stream()
                .sorted((p1, p2) -> p1.getPokemonId().compareTo(p2.getPokemonId()))
                .toList();
    }
}
