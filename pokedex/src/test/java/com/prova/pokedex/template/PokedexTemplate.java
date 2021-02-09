package com.prova.pokedex.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.prova.pokedex.enums.PokeBolasEnum;
import com.prova.pokedex.model.entrada.PokedexEntrada;

public class PokedexTemplate implements TemplateLoader {
    public static final String VALIDO = "valido";
    public static final String IDPOKEMON_INVALIDO = "invalido_idPokemon";
    public static final String NIVEL_INVALIDO =  "invalido_nivel";
    public static final String LOCAL_INVALIDO = "invalido_local";
    public static final String USUARIO_INVALIDO ="invalido_usuario" ;

    @Override
    public void load() {
        Fixture.of(PokedexEntrada.class).addTemplate(VALIDO,new Rule(){
            {
                add("idPokemon",1);
                add("nivelPokemon",10);
                add("idLocal",1);
                add("idUsuario",1);
                add("pokeBolasEnum",PokeBolasEnum.POKEBALL);
            }
        }).addTemplate(IDPOKEMON_INVALIDO).inherits(VALIDO,new Rule() {
            {
                add("idPokemon",0);
            }
        }).addTemplate(NIVEL_INVALIDO).inherits(VALIDO,new Rule() {
            {
                add("nivelPokemon",1);
            }
        }).addTemplate(LOCAL_INVALIDO).inherits(VALIDO,new Rule() {
            {
                add("idLocal",0);
            }
        }).addTemplate(USUARIO_INVALIDO).inherits(VALIDO,new Rule() {
            {
                add("idUsuario",0);
            }
        });
    }
}
