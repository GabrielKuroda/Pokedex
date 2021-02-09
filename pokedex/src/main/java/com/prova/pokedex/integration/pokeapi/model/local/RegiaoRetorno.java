package com.prova.pokedex.integration.pokeapi.model.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegiaoRetorno {

    @JsonProperty("name")
    private String nomeRegiao;

}
