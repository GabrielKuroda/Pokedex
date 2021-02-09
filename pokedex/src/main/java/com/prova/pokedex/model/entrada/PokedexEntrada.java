package com.prova.pokedex.model.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prova.pokedex.enums.PokeBolasEnum;
import lombok.Data;

@Data
public class PokedexEntrada {

    @JsonProperty("idPokemon")
    private Integer idPokemon;

    @JsonProperty("nivelPokemon")
    private Integer nivelPokemon;

    @JsonProperty("idLocal")
    private Integer idLocal;

    @JsonProperty("idUsuario")
    private Integer idUsuario;

    @JsonProperty("tipoPokebola")
    private PokeBolasEnum pokeBolasEnum;

}
