package com.troca.trocaPokemon.model.saida;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.model.enums.StatusEnum;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrocaSaida {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("pokemonTroca")
    private PokedexRetorno pokemonTroca;

    @JsonProperty("data")
    private LocalDate data;

    @JsonProperty("status")
    private StatusEnum statusEnum;
}
