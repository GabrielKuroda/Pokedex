package com.troca.trocaPokemon.integration.model.pokedex;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TipoRetorno {

    @JsonProperty("name")
    private String nomeTipo;

}
