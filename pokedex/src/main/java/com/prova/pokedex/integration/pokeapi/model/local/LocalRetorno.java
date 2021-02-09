package com.prova.pokedex.integration.pokeapi.model.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocalRetorno {

    @JsonProperty("id")
    private Integer idLocal;

    @JsonProperty("name")
    private String nomeLocal;

    @JsonProperty("region")
    private RegiaoRetorno regiao;

}
