package com.troca.trocaPokemon.validator;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.service.PokedexService;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import com.troca.trocaPokemon.service.TrocaService;
import com.troca.trocaPokemon.template.PokedexRetornoTemplate;
import com.troca.trocaPokemon.template.TrocaEntradaTemplate;
import com.troca.trocaPokemon.template.TrocaSaidaTemplate;
import com.troca.trocaPokemon.validator.exception.IdException;
import com.troca.trocaPokemon.validator.exception.RegraNegocioException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrocaValidatorTeste {

    @InjectMocks
    private TrocaValidator trocaValidator;

    @Mock
    private PokedexService pokedexService;

    @Mock
    private TrocaService trocaService;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates(TrocaEntradaTemplate.class.getPackage().getName());
        FixtureFactoryLoader.loadTemplates(TrocaSaidaTemplate.class.getPackage().getName());
        FixtureFactoryLoader.loadTemplates(PokedexRetornoTemplate.class.getPackage().getName());
    }

    @Test
    public void validaNovaTrocaSucesso() {

        TrocaEntrada trocaEntrada = Fixture.from(TrocaEntrada.class).gimme(TrocaEntradaTemplate.VALIDO);

        List<PokedexRetorno> pokedexRetornoList = new ArrayList<>();
        pokedexRetornoList.add(new PokedexRetorno());
        pokedexRetornoList.add(new PokedexRetorno());

        when(pokedexService.pegaRegistros(any())).thenReturn(pokedexRetornoList);
        when(trocaService.trocaExiste(any())).thenReturn(false);

        try {
            trocaValidator.validaNovaTroca(trocaEntrada);
            System.out.println("OK");
        } catch (RuntimeException runtimeException) {
            Assert.assertEquals("Erro", runtimeException.getMessage());
            System.out.println(runtimeException.getMessage());
        }

    }

    @Test
    public void validaNovaTrocaIdPokemonTrocaErro() {

        TrocaEntrada trocaEntrada = Fixture.from(TrocaEntrada.class).gimme(TrocaEntradaTemplate.INVALIDO);

        List<PokedexRetorno> pokedexRetornoList = new ArrayList<>();
        pokedexRetornoList.add(new PokedexRetorno());
        pokedexRetornoList.add(new PokedexRetorno());

        try {
            trocaValidator.validaNovaTroca(trocaEntrada);
            System.out.println("OK");
        } catch (IdException idException) {
            Assert.assertEquals("Id do(a) Pokemon Inválido!", idException.getMessage());
            System.out.println(idException.getMessage());
        }

    }

    @Test
    public void validaNovaTrocaPokemonUnicoErro() {

        TrocaEntrada trocaEntrada = Fixture.from(TrocaEntrada.class).gimme(TrocaEntradaTemplate.VALIDO);

        List<PokedexRetorno> pokedexRetornoList = new ArrayList<>();
        pokedexRetornoList.add(new PokedexRetorno());

        when(pokedexService.pegaRegistros(any())).thenReturn(pokedexRetornoList);

        try {
            trocaValidator.validaNovaTroca(trocaEntrada);
            System.out.println("OK");
        } catch (RegraNegocioException regraNegocioException) {
            Assert.assertEquals("Erro: Você não pode trocar esse Pokemon", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }

    }

    @Test
    public void verificaLimitePokemonSucesso() {

        int quantidadePokemonIguaisADaTrocaSolicitada = 1;
        int quantidadePokemonIguaisQueOCriadotDaTrocaTem = 1;

        try{
            trocaValidator.verificaLimitePokemon(quantidadePokemonIguaisADaTrocaSolicitada,quantidadePokemonIguaisQueOCriadotDaTrocaTem);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você já possui o Limite desse Pokemon", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }

    }

    @Test
    public void verificaLimitePokemonLimiteSolicitadoErro() {

        int quantidadePokemonIguaisADaTrocaSolicitada = 3;
        int quantidadePokemonIguaisQueOCriadotDaTrocaTem = 1;

        try{
            trocaValidator.verificaLimitePokemon(quantidadePokemonIguaisADaTrocaSolicitada,quantidadePokemonIguaisQueOCriadotDaTrocaTem);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você já possui o Limite desse Pokemon", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }

    }

    @Test
    public void verificaLimitePokemonLimiteCriadorTrocaErro() {

        int quantidadePokemonIguaisADaTrocaSolicitada = 1;
        int quantidadePokemonIguaisQueOCriadotDaTrocaTem = 3;

        try{
            trocaValidator.verificaLimitePokemon(quantidadePokemonIguaisADaTrocaSolicitada,quantidadePokemonIguaisQueOCriadotDaTrocaTem);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: O outro treinador já possui o Limite desse Pokemon", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void verificaTrocaConsigoMesmoSucesso(){
        PokedexRetorno pokedexRetorno = Fixture.from(PokedexRetorno.class).gimme(PokedexRetornoTemplate.VALIDO_ID2);
        TrocaSaida trocaSaida = Fixture.from(TrocaSaida.class).gimme(TrocaSaidaTemplate.VALIDO);

        try{
            trocaValidator.verificaTrocaConsigoMesmo(pokedexRetorno,trocaSaida);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode trocar com você mesmo!", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void verificaTrocaConsigoMesmoErro(){
        PokedexRetorno pokedexRetorno = Fixture.from(PokedexRetorno.class).gimme(PokedexRetornoTemplate.VALIDO);
        TrocaSaida trocaSaida = Fixture.from(TrocaSaida.class).gimme(TrocaSaidaTemplate.VALIDO);

        try{
            trocaValidator.verificaTrocaConsigoMesmo(pokedexRetorno,trocaSaida);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode trocar com você mesmo!", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }

    }

    @Test
    public void verificaSePokemonEUnicoSucesso(){
        int quantidadePokemon = 2;

        try{
            trocaValidator.verificaSePokemonEUnico(quantidadePokemon);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode trocar esse Pokemon", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void verificaSePokemonEUnicoErro(){
        int quantidadePokemon = 1;

        try{
            trocaValidator.verificaSePokemonEUnico(quantidadePokemon);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode trocar esse Pokemon", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void verificaPokemonIgual(){
        PokedexRetorno pokedexRetorno = Fixture.from(PokedexRetorno.class).gimme(PokedexRetornoTemplate.VALIDO);
        TrocaSaida trocaSaida = Fixture.from(TrocaSaida.class).gimme(TrocaSaidaTemplate.VALIDO);

        try{
            trocaValidator.verificaPokemonIgual(pokedexRetorno,trocaSaida);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode trocar pokemon semelhantes!", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void verificaPokemonIgualErro(){
        PokedexRetorno pokedexRetorno = Fixture.from(PokedexRetorno.class).gimme(PokedexRetornoTemplate.VALIDO_ID2);
        TrocaSaida trocaSaida = Fixture.from(TrocaSaida.class).gimme(TrocaSaidaTemplate.VALIDO);

        try{
            trocaValidator.verificaPokemonIgual(pokedexRetorno,trocaSaida);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode trocar pokemon semelhantes!", regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }

    }

}
