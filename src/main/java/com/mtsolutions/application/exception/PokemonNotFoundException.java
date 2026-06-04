package com.mtsolutions.application.exception;

import com.mtsolutions.domain.constant.Errors;
import com.mtsolutions.domain.constant.Origin;

public class PokemonNotFoundException extends PokemonDuelsException {

    private static final Errors ERROR_CODE = Errors.POKEMON_NOT_FOUND_EXCEPTION;
    private static final Integer STATUS_CODE = 404;
    private static final String DEFAULT_MESSAGE = Errors.POKEMON_NOT_FOUND_EXCEPTION.getDisplayName();

    public PokemonNotFoundException() {
        super(DEFAULT_MESSAGE, STATUS_CODE, ERROR_CODE, Origin.POKEMON_DUELS);
    }
}
