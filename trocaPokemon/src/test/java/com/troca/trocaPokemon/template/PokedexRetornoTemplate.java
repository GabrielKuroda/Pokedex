package com.troca.trocaPokemon.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.model.pokedex.PokemonRetorno;
import com.troca.trocaPokemon.integration.model.user.UsuarioSaida;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;

public class PokedexRetornoTemplate implements TemplateLoader {
    public static final String VALIDO = "valido";
    public static final String VALIDO_ID2 = "valido2";

    @Override
    public void load() {
        Fixture.of(PokedexRetorno.class).addTemplate(VALIDO,new Rule(){
            {
                UsuarioSaida usuarioSaida = new UsuarioSaida();
                usuarioSaida.setId(1);

                add("usuario",usuarioSaida);

                PokemonRetorno pokemonRetorno = new PokemonRetorno();
                pokemonRetorno.setIdPokemon(1);
                add("pokemon",pokemonRetorno);
            }
        }).addTemplate(VALIDO_ID2).inherits(VALIDO,new Rule(){
            {
                UsuarioSaida usuarioSaida = new UsuarioSaida();
                usuarioSaida.setId(2);

                add("usuario",usuarioSaida);

                PokemonRetorno pokemonRetorno = new PokemonRetorno();
                pokemonRetorno.setIdPokemon(2);
                add("pokemon",pokemonRetorno);
            }
        });
    }

}
