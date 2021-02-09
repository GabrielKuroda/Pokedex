package com.troca.trocaPokemon.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.model.pokedex.PokemonRetorno;
import com.troca.trocaPokemon.integration.model.user.UsuarioSaida;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.enums.StatusEnum;
import com.troca.trocaPokemon.model.saida.TrocaSaida;

import java.time.LocalDate;

public class TrocaSaidaTemplate implements TemplateLoader {
    public static final String VALIDO = "valido";

    @Override
    public void load() {
        Fixture.of(TrocaSaida.class).addTemplate(VALIDO,new Rule(){
            {
                add("id",1);

                PokedexRetorno pokedexRetorno = new PokedexRetorno();
                pokedexRetorno.setId(1);

                UsuarioSaida usuarioSaida = new UsuarioSaida();
                usuarioSaida.setId(1);

                pokedexRetorno.setUsuario(usuarioSaida);

                PokemonRetorno pokemonRetorno = new PokemonRetorno();
                pokemonRetorno.setIdPokemon(1);

                pokedexRetorno.setPokemon(pokemonRetorno);

                add("pokemonTroca",pokedexRetorno);
                add("data",LocalDate.now());
                add("statusEnum",StatusEnum.ABERTA);
            }
        });
    }

}
