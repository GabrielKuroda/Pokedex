package com.prova.pokedex.model.saida;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prova.pokedex.enums.PokeBolasEnum;
import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.integration.pokeapi.model.local.LocalRetorno;
import com.prova.pokedex.integration.pokeapi.model.pokemon.PokemonRetorno;
import com.prova.pokedex.integration.userapi.model.UsuarioRetorno;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PokedexSaida {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("pokemon")
    private PokemonRetorno pokemon;

    @JsonProperty("nivel_pokemon")
    private Integer nivelPokemon;

    @JsonProperty("data")
    private LocalDate dataCaptura;

    @JsonProperty("local")
    private LocalRetorno local;

    @JsonProperty("treinador")
    private UsuarioRetorno usuario;

    @JsonProperty("tipoPokebola")
    private PokeBolasEnum pokeBolasEnum;

    @JsonProperty("status")
    private StatusEnum statusEnum;

}
