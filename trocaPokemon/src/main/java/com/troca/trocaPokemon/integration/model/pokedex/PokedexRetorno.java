package com.troca.trocaPokemon.integration.model.pokedex;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troca.trocaPokemon.integration.model.user.UsuarioSaida;
import com.troca.trocaPokemon.integration.model.enums.PokeBolasEnum;
import com.troca.trocaPokemon.integration.model.enums.StatusEnumPokedex;
import lombok.Data;

@Data
public class PokedexRetorno {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("pokemon")
    private PokemonRetorno pokemon;

    @JsonProperty("nivel_pokemon")
    private Integer nivelPokemon;

    @JsonProperty("treinador")
    private UsuarioSaida usuario;

    @JsonProperty("tipoPokebola")
    private PokeBolasEnum pokeBolasEnum;

    @JsonProperty("status")
    private StatusEnumPokedex statusEnumPokedex;

}
