package com.mtsolutions.domain.constant;

import lombok.Getter;

@Getter
public enum Origin {
    POKEMON_DUELS("POKEMON_DUELS"),
    POKE_API("POKE_API");

    private final String displayName;

    Origin(String displayName) {
        this.displayName = displayName;
    }
}
