package com.mtsolutions.domain.entity;

import com.mtsolutions.domain.model.PokemonGifs;
import com.mtsolutions.domain.model.PokemonMove;
import com.mtsolutions.domain.model.PokemonStat;
import com.mtsolutions.domain.model.PokemonType;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@MongoEntity(collection = "pokemon")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Pokemon {

    @Id
    private Integer pokemonId;
    private String name;
    private String profileImageUrl;
    private PokemonGifs pokemonGifs;
    private List<PokemonType> types;
    private List<PokemonStat> stats;
    private List<PokemonMove> moves;
}
