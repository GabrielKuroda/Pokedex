package com.prova.pokedex.integration.pokeapi.service;

import com.prova.pokedex.integration.pokeapi.model.pokemon.PokemonRetorno;
import com.prova.pokedex.validator.exception.DadoApiExternaNotFound;
import com.prova.pokedex.integration.pokeapi.client.local.LocalClient;
import com.prova.pokedex.integration.pokeapi.client.pokemon.PokemonClient;
import com.prova.pokedex.integration.pokeapi.model.local.LocalRetorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokeAPIService {

    @Autowired
    private PokemonClient pokemonClient;

    @Autowired
    private LocalClient localClient;

    public PokemonRetorno getPokemon(Integer idPokemon){
        try{
            return pokemonClient.getPokemon(idPokemon);
        }catch (RuntimeException ex){
            throw new DadoApiExternaNotFound("Pokemon");
        }
    }

    public LocalRetorno getLocal(Integer idLocal){
        try {
            return localClient.getLocal(idLocal);
        }catch (RuntimeException ex){
            throw new DadoApiExternaNotFound("Local");
        }
    }

}
