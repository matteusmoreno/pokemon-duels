package com.mtsolutions.application.resource.rest;

import com.mtsolutions.domain.controller.PokemonController;
import com.mtsolutions.domain.dto.CreatePokemonRequest;
import com.mtsolutions.domain.entity.Pokemon;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/pokemon")
@Tag(name = "Pokemon Resource", description = "Endpoints for managing Pokemon")
public class PokemonResource {

    private final PokemonController pokemonController;

    public PokemonResource(PokemonController pokemonController) {
        this.pokemonController = pokemonController;
    }

    @POST
    @Path("/create")
    public Response create(CreatePokemonRequest request) {
        Pokemon pokemon = this.pokemonController.createPokemon(request);

        return Response.status(Response.Status.CREATED).entity(pokemon).build();
    }
}
