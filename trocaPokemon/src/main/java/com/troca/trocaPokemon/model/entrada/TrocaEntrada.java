package com.troca.trocaPokemon.model.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TrocaEntrada {

    @JsonProperty("pokemonTroca")
    private Integer pokemonTroca;

}
