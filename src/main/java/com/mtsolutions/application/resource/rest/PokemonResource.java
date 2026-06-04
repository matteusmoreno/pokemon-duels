package com.mtsolutions.application.resource.rest;

import com.mtsolutions.application.common.RequestParam;
import com.mtsolutions.application.resource.rest.examples.PokemonResourceExamples;
import com.mtsolutions.domain.controller.PokemonController;
import com.mtsolutions.domain.dto.ShortPokemonResponseDto;
import com.mtsolutions.domain.entity.Pokemon;
import com.mtsolutions.domain.model.PagedResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@RequestScoped
@Path("/v1/pokemon")
@Tag(name = "Pokemon Resource", description = "Endpoints for managing Pokemon")
public class PokemonResource {

    private final PokemonController pokemonController;

    public PokemonResource(PokemonController pokemonController) {
        this.pokemonController = pokemonController;
    }

    @POST
    @Path("/{name}")
    @Operation(
            summary = "Create a new Pokemon",
            description = "Creates a new Pokemon with the specified name. The name must be unique and will be stored in lowercase. Returns the created Pokemon entity."
    )
    @APIResponse(
            responseCode = "201",
            description = "Pokemon created successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = @ExampleObject(
                            name = "Created Pokemon",
                            value = PokemonResourceExamples.POKEMON_CREATED_RESPONSE
                    )
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Pokemon not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = @ExampleObject(
                            name = "Pokemon Not Found",
                            value = PokemonResourceExamples.POKEMON_NOT_FOUND_RESPONSE
                    )
            )
    )
    public Response create(@PathParam(RequestParam.NAME) String name) {
        Pokemon pokemon = this.pokemonController.createPokemon(name.toLowerCase());

        return Response.status(Response.Status.CREATED).entity(pokemon).build();
    }

    @GET
    @Path("/{pokemonId}")
    @Operation(
            summary = "Find Pokemon by Pokemon ID",
            description = "Retrieves a Pokemon entity by its unique ID. Returns the Pokemon if found, or a 404 error if no Pokemon with the specified ID exists."
    )
    @APIResponse(
        responseCode = "200",
        description = "Pokemon found successfully",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                examples = @ExampleObject(
                        name = "Found Pokemon",
                        value = PokemonResourceExamples.FIND_POKEMON_RESPONSE
                )
        )
    )
    @APIResponse(
            responseCode = "404",
            description = "Pokemon not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = @ExampleObject(
                            name = "Pokemon Not Found",
                            value = PokemonResourceExamples.POKEMON_NOT_FOUND_RESPONSE
                    )
            )
    )
    public Response findByPokemonId(@PathParam(RequestParam.POKEMON_ID) Integer pokemonId) {
        Pokemon pokemon = this.pokemonController.findByPokemonId(pokemonId);

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

    @GET
    @Path("/{name}")
    @Operation(
            summary = "Find Pokemon by name",
            description = "Retrieves a Pokemon entity by its unique name. The search is case-insensitive. Returns the Pokemon if found, or a 404 error if no Pokemon with the specified name exists."
    )
    @APIResponse(
            responseCode = "200",
            description = "Pokemon found successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = @ExampleObject(
                            name = "Found Pokemon",
                            value = PokemonResourceExamples.FIND_POKEMON_RESPONSE
                    )
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Pokemon not found",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = @ExampleObject(
                            name = "Pokemon Not Found",
                            value = PokemonResourceExamples.POKEMON_NOT_FOUND_RESPONSE
                    )
            )
    )
    public Response findByName(@PathParam(RequestParam.NAME) String name) {
        Pokemon pokemon = this.pokemonController.findPokemonByName(name);

        return Response.status(Response.Status.OK).entity(pokemon).build();
    }

    @GET
    @Path("/all")
    @Operation(
            summary = "Find all Pokemons with pagination",
            description = "Retrieves a paginated list of all Pokemons. Accepts 'page' (0-based) and 'size' query parameters for pagination. Returns a paged response containing short Pokemon details sorted bu Pokemon ID."
    )
    @APIResponse(
            responseCode = "200",
            description = "Pokemons listed successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = @ExampleObject(
                            name = "All Pokemons",
                            value = PokemonResourceExamples.POKEMON_LIST_RESPONSE
                    )
            )
    )
    public Response findAll(
            @QueryParam(RequestParam.PAGE) @DefaultValue("0") int page,
            @QueryParam(RequestParam.SIZE) @DefaultValue("10") int size
    ) {
        PagedResponse<ShortPokemonResponseDto> response = this.pokemonController.findAllPokemons(page, size);

        return Response.status(Response.Status.OK).entity(response).build();

    }
}
