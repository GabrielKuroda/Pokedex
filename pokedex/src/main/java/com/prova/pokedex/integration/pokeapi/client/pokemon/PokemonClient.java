package com.prova.pokedex.integration.pokeapi.client.pokemon;

import com.prova.pokedex.integration.pokeapi.model.pokemon.PokemonRetorno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemon",url = "${pokemon.endpoint}")
public interface PokemonClient {

    @GetMapping("/{idPokemon}")
    PokemonRetorno getPokemon(@PathVariable(name = "idPokemon")Integer idPokemon);

}
