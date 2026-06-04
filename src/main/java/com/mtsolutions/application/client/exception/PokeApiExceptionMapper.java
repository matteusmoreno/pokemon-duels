package com.mtsolutions.application.client.exception;

import com.mtsolutions.application.exception.PokemonNotFoundException;
import com.mtsolutions.domain.constant.Errors;
import com.mtsolutions.domain.constant.Origin;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Slf4j
public class PokeApiExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        if (response.getStatus() == 404) {
            String defaultMessage = Errors.POKEMON_NOT_FOUND_EXCEPTION.getDisplayName();
            Integer statusCode = 404;
            Errors errorCode = Errors.POKEMON_NOT_FOUND_EXCEPTION;

            String externalErrorMessage = "";
            if (response.hasEntity()) {
                externalErrorMessage = response.readEntity(String.class);
            }
            log.warn("PokeAPI returned 404 Not Found");
            return new PokemonNotFoundException(defaultMessage, statusCode, errorCode, Origin.POKE_API, externalErrorMessage);
        }

        // Retornar null faz com que o Quarkus siga o fluxo padrão
        // para outros erros (como 500, etc) lançando uma WebApplicationException
        return null;
    }
}