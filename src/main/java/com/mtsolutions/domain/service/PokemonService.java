package com.mtsolutions.domain.service;

import com.mtsolutions.application.client.PokeApiClient;
import com.mtsolutions.application.client.dto.*;
import com.mtsolutions.domain.dto.ShortPokemonResponseDto;
import com.mtsolutions.domain.entity.Pokemon;
import com.mtsolutions.domain.model.*;
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

    public Pokemon createPokemon(String name) {
        log.info("Creating Pokemon");

        if (Boolean.TRUE.equals(this.pokemonRepository.existsByName(name))) {
            log.warn("Pokemon already exists. Returning existing Pokemon");
            return this.pokemonRepository.findByName(name);
        }

        PokeApiPokemonResponse apiResponse = this.pokeApiClient.getPokemonByName(name);
        List<PokemonMove> pokemonMoves = this.fetchPokemonMoves(apiResponse.moves());

        Pokemon pokemon = Pokemon.builder()
                .pokemonId(apiResponse.id())
                .name(apiResponse.name())
                .profileImageUrl(apiResponse.sprites().other().officialArtwork().frontDefault())
                .pokemonGifs(new PokemonGifs(apiResponse.sprites()))
                .types(this.fetchPokemonTypes(apiResponse.types()))
                .stats(this.fetchPokemonStats(apiResponse.stats()))
                .moves(pokemonMoves)
                .build();

        this.pokemonRepository.persist(pokemon);
        log.info("Pokemon with name '{}' has been created.", pokemon.getName());
        return pokemon;
    }

    public Pokemon findPokemonByPokemonId(Integer pokemonId) {
        return this.pokemonRepository.findByPokemonId(pokemonId);
    }

    public Pokemon findPokemonByName(String name) {
        return this.pokemonRepository.findByName(name);
    }

    public PagedResponse<ShortPokemonResponseDto> findAllPokemons(int page, int size) {
        long total  = this.pokemonRepository.count();
        List<ShortPokemonResponseDto> content = this.pokemonRepository.findAllSortedByPokemonId(page, size)
                .stream()
                .map(ShortPokemonResponseDto::new)
                .toList();

        return PagedResponse.of(content, page, size, total);
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
                default -> log.warn("Unknown stat '{}' with value '{}' for Pokemon. Skipping.", statName, base);
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
                            .id(moveDetails.id())
                            .name(moveDto.move().name())
                            .power(moveDetails.power())
                            .accuracy(moveDetails.accuracy())
                            .pp(moveDetails.pp())
                            .build();
                })
                .toList();
    }
}
