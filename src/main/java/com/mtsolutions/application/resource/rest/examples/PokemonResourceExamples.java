package com.mtsolutions.application.resource.rest.examples;

public class PokemonResourceExamples {

    public static final String POKEMON_CREATED_RESPONSE =
            """
                            {
                              "pokemonId": 25,
                              "name": "pikachu",
                              "profileImageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                              "pokemonGifs": {
                                "animationFront": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/25.gif",
                                "animationBack": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/back/25.gif"
                              },
                              "types": [
                                {
                                  "name": "electric"
                                }
                              ],
                              "stats": [
                                {
                                  "hp": 35,
                                  "attack": 55,
                                  "defense": 40,
                                  "specialAttack": 50,
                                  "specialDefense": 50,
                                  "speed": 90
                                }
                              ],
                              "moves": [
                                {
                                  "id": 5,
                                  "name": "mega-punch",
                                  "accuracy": 85,
                                  "power": 80,
                                  "pp": 20
                                },
                                {
                                  "id": 918,
                                  "name": "upper-hand",
                                  "accuracy": 100,
                                  "power": 65,
                                  "pp": 15
                                }
                              ]
                            }
                    """;

    public static final String POKEMON_NOT_FOUND_RESPONSE =
            """
                            {
                                      "errorCode": "POKEMON_NOT_FOUND_EXCEPTION",
                                      "message": "Pokemon not found",
                                      "status": 404,
                                      "path": "/v1/pokemon/asd",
                                      "timestamp": "2026-06-04T19:46:05.713299",
                                      "origin": "POKE_API",
                                      "externalResponse": "Not Found"
                                    }
                    """;
    public static final String FIND_POKEMON_RESPONSE =
            """
                            {
                              "pokemonId": 25,
                              "name": "pikachu",
                              "profileImageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                              "pokemonGifs": {
                                "animationFront": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/25.gif",
                                "animationBack": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/back/25.gif"
                              },
                              "types": [
                                {
                                  "name": "electric"
                                }
                              ],
                              "stats": [
                                {
                                  "hp": 35,
                                  "attack": 55,
                                  "defense": 40,
                                  "specialAttack": 50,
                                  "specialDefense": 50,
                                  "speed": 90
                                }
                              ],
                              "moves": [
                                {
                                  "id": 5,
                                  "name": "mega-punch",
                                  "accuracy": 85,
                                  "power": 80,
                                  "pp": 20
                                },
                                {
                                  "id": 918,
                                  "name": "upper-hand",
                                  "accuracy": 100,
                                  "power": 65,
                                  "pp": 15
                                }
                              ]
                            }
                    """;

    public static final String POKEMON_LIST_RESPONSE =
            """
                            {
                              "content": [
                                {
                                  "pokemonId": 1,
                                  "name": "bulbasaur",
                                  "profileImageUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                                  "types": [
                                    {
                                      "name": "grass"
                                    },
                                    {
                                      "name": "poison"
                                    }
                                  ],
                                  "stats": [
                                    {
                                      "hp": 45,
                                      "attack": 49,
                                      "defense": 49,
                                      "specialAttack": 65,
                                      "specialDefense": 65,
                                      "speed": 45
                                    }
                                  ]
                                }
                              ],
                              "page": 0,
                              "size": 10,
                              "totalElements": 1,
                              "totalPages": 1
                            }
                    """;

    private PokemonResourceExamples() {}
}
