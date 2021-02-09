package com.troca.trocaPokemon.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;

public class TrocaEntradaTemplate implements TemplateLoader {
    public static final String VALIDO = "valido";
    public static final String INVALIDO = "invalido";

    @Override
    public void load() {
        Fixture.of(TrocaEntrada.class).addTemplate(VALIDO,new Rule(){
            {
                add("pokemonTroca",1);
            }
        }).addTemplate(INVALIDO).inherits(VALIDO,new Rule(){
            {
                add("pokemonTroca",0);
            }
        });
    }
}
