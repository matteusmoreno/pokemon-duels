package com.mtsolutions.domain.constant;

import lombok.Getter;

@Getter
public enum Errors {

    POKEMON_NOT_FOUND_EXCEPTION("Pokemon not found");

    private final String displayName;

    Errors(String displayName) {
        this.displayName = displayName;
    }
}
