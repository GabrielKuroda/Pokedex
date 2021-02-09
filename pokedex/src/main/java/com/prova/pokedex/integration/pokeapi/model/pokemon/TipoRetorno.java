package com.prova.pokedex.integration.pokeapi.model.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TipoRetorno {

    @JsonProperty("name")
    private String nomeTipo;

}
