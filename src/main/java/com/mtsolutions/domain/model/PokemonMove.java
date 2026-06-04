package com.mtsolutions.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class PokemonMove {

    private Integer id;
    private String name;
    private Integer accuracy;
    private Integer power;
    private Integer pp;
}
