package com.mtsolutions.domain.service;

import com.mtsolutions.application.client.PokeApiClient;
import com.mtsolutions.application.dto.*;
import com.mtsolutions.domain.dto.CreatePokemonRequest;
import com.mtsolutions.domain.entity.Pokemon;
import com.mtsolutions.domain.model.PokemonMove;
import com.mtsolutions.domain.model.PokemonStat;
import com.mtsolutions.domain.model.PokemonType;
import com.mtsolutions.domain.model.PokemonGifs;
import com.mtsolutions.domain.repository.PokemonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@Slf4j
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokeApiClient pokeApiClient;

    public PokemonService(
            PokemonRepository pokemonRepository,
            @RestClient PokeApiClient pokeApiClient) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiClient = pokeApiClient;
    }

    public Pokemon createPokemon(CreatePokemonRequest request) {
        log.info("Creating Pokemon with name '{}'", request.name());

        if (this.pokemonRepository.existsByName(request.name())) {
            log.warn("Pokemon with name '{}' already exists. Returning existing Pokemon.", request.name());
            return this.pokemonRepository.findByName(request.name());
        }

        PokeApiPokemonResponse apiResponse = this.pokeApiClient.getPokemonByName(request.name());
        List<PokemonMove> pokemonMoves = this.fetchPokemonMoves(apiResponse.moves());

        Pokemon pokemon = Pokemon.builder()
                .pokemonId(apiResponse.id())
                .name(request.name())
                .profileImageUrl(apiResponse.sprites().other().officialArtwork().frontDefault())
                .pokemonGifs(new PokemonGifs(apiResponse.sprites()))
                .types(this.fetchPokemonTypes(apiResponse.types()))
                .stats(this.fetchPokemonStats(apiResponse.stats()))
                .moves(pokemonMoves)
                .build();

        this.pokemonRepository.persist(pokemon);
        log.info("Pokemon with name '{}' has been created.", request.name());
        return pokemon;
    }













    protected List<PokemonStat> fetchPokemonStats(List<PokeApiStatsResponseDto> statsResponseDtos) {
        if (statsResponseDtos == null || statsResponseDtos.isEmpty()) {
            return List.of();
        }

        Integer hp = null;
        Integer attack = null;
        Integer defense = null;
        Integer specialAttack = null;
        Integer specialDefense = null;
        Integer speed = null;

        for (PokeApiStatsResponseDto dto : statsResponseDtos) {
            String statName = dto.stat().name();
            Integer base = dto.baseStat();
            switch (statName) {
                case "hp" -> hp = base;
                case "attack" -> attack = base;
                case "defense" -> defense = base;
                case "special-attack" -> specialAttack = base;
                case "special-defense" -> specialDefense = base;
                case "speed" -> speed = base;
                default -> {}
            }
        }

        PokemonStat pokemonStat = PokemonStat.builder()
                .hp(hp)
                .attack(attack)
                .defense(defense)
                .specialAttack(specialAttack)
                .specialDefense(specialDefense)
                .speed(speed)
                .build();

        return List.of(pokemonStat);
    }

    protected List<PokemonType> fetchPokemonTypes(List<PokeApiTypesResponseDto> typesResponseDtos) {
        return typesResponseDtos.stream()
                .map(typeDto -> new PokemonType(typeDto.type().name()))
                .toList();
    }

    protected List<PokemonMove> fetchPokemonMoves(List<PokeApiMovesResponseDto> movesResponseDtos) {
        return movesResponseDtos.stream()
                .map(moveDto -> {
                    PokeApiMoveResponse moveDetails = this.pokeApiClient.getMove(moveDto.move().name());
                    return PokemonMove.builder()
                            .name(moveDto.move().name())
                            .power(moveDetails.power())
                            .accuracy(moveDetails.accuracy())
                            .pp(moveDetails.pp())
                            .build();
                })
                .toList();
    }
}
