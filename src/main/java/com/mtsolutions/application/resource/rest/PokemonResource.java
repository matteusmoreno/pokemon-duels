package com.mtsolutions.application.resource.rest;

import com.mtsolutions.application.common.RequestParam;
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
    public Response create(@PathParam(RequestParam.NAME) String name) {
        Pokemon pokemon = this.pokemonController.createPokemon(name.toLowerCase());

        return Response.status(Response.Status.CREATED).entity(pokemon).build();
    }

    @GET
    @Path("/{pokemonId}")
    public Response findByPokemonId(@PathParam(RequestParam.POKEMON_ID) Integer pokemonId) {
        Pokemon pokemon = this.pokemonController.findByPokemonId(pokemonId);

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

    @GET
    @Path("/{name}")
    public Response findByName(@PathParam(RequestParam.NAME) String name) {
        Pokemon pokemon = this.pokemonController.findPokemonByName(name);

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

    @GET
    @Path("/all")
    public Response findAll(
            @QueryParam(RequestParam.PAGE) @DefaultValue("0") int page,
            @QueryParam(RequestParam.SIZE) @DefaultValue("10") int size
    ) {
        PagedResponse<ShortPokemonResponseDto> response = this.pokemonController.findAllPokemons(page, size);

        return Response.status(Response.Status.OK).entity(response).build();

    }
}
