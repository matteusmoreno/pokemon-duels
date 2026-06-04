package com.mtsolutions.application.resource.rest;

import com.mtsolutions.domain.controller.PokemonController;
import com.mtsolutions.domain.dto.ShortPokemonResponseDto;
import com.mtsolutions.domain.entity.Pokemon;
import com.mtsolutions.domain.model.PagedResponse;
import jakarta.ws.rs.*;
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
    @Path("/{name}")
    public Response create(@PathParam("name") String name) {
        Pokemon pokemon = this.pokemonController.createPokemon(name.toLowerCase());

        return Response.status(Response.Status.CREATED).entity(pokemon).build();
    }

    @GET
    @Path("/{name}")
    public Response findByName(@PathParam("name") String name) {
        Pokemon pokemon = this.pokemonController.findPokemonByName(name);

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

    @GET
    @Path("/all")
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        PagedResponse<ShortPokemonResponseDto> response = this.pokemonController.findAllPokemons(page, size);

        return Response.status(Response.Status.OK).entity(response).build();

    }
}
