package com.troca.trocaPokemon.integration.model.pokedex;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonRetorno {

    @JsonProperty("id")
    private Integer idPokemon;

    @JsonProperty("name")
    private String nomePokemon;

    @JsonProperty("types")
    private List<Tipo> tipoPokemon;

    @Data
    static class Tipo{
        @JsonProperty("type")
        private TipoRetorno tipoPokemon;
    }

}
