package com.mtsolutions.domain.model;

import com.mtsolutions.application.client.dto.PokeApiSpritesResponseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class PokemonGifs {
    private String animationFront;
    private String animationBack;

    public PokemonGifs(PokeApiSpritesResponseDto sprites) {
        this.animationFront = sprites.other().showdown().frontDefault();
        this.animationBack = sprites.other().showdown().backDefault();
    }
}
