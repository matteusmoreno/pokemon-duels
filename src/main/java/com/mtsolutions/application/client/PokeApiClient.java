package com.mtsolutions.application.client;

import com.mtsolutions.application.dto.PokeApiMoveResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.mtsolutions.application.dto.PokeApiPokemonResponse;

@Path("/")
@RegisterRestClient(configKey = "pokeapi")
public interface PokeApiClient {

	@GET
	@Path("pokemon/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	PokeApiPokemonResponse getPokemonByName(@PathParam("name") String name);

	@GET
	@Path("move/{name}")
	@Produces(MediaType.APPLICATION_JSON)
    PokeApiMoveResponse getMove(@PathParam("name") String name);
}
