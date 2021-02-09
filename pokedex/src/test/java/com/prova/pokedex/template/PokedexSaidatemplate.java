package com.prova.pokedex.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prova.pokedex.enums.PokeBolasEnum;
import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.integration.pokeapi.model.local.LocalRetorno;
import com.prova.pokedex.integration.pokeapi.model.pokemon.PokemonRetorno;
import com.prova.pokedex.integration.userapi.model.UsuarioRetorno;
import com.prova.pokedex.model.saida.PokedexSaida;

import java.time.LocalDate;

public class PokedexSaidatemplate implements TemplateLoader {
    public static final String VALIDO = "valido";
    public static final String INVALIDO = "invalido";

    @Override
    public void load() {
        Fixture.of(PokedexSaida.class).addTemplate(VALIDO,new Rule(){
            {
                add("id",1);

                PokemonRetorno pokemonRetorno = new PokemonRetorno();
                pokemonRetorno.setIdPokemon(1);

                add("pokemon",pokemonRetorno);
                add("nivelPokemon",10);
                add("dataCaptura",LocalDate.now());
                add("local",new LocalRetorno());

                UsuarioRetorno usuarioRetorno = new UsuarioRetorno();
                usuarioRetorno.setIdUsuario(1);

                add("usuario",usuarioRetorno);
                add("pokeBolasEnum",PokeBolasEnum.POKEBALL);
                add("statusEnum",StatusEnum.EM_POSSE);
            }
        }).addTemplate(INVALIDO).inherits(VALIDO,new Rule(){
            {
                add("statusEnum",StatusEnum.EM_TROCA);
            }
        });
    }
}
