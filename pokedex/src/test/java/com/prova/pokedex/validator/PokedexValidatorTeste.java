package com.prova.pokedex.validator;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.prova.pokedex.model.entrada.PokedexEntrada;
import com.prova.pokedex.model.saida.PokedexSaida;
import com.prova.pokedex.service.PokedexService;
import com.prova.pokedex.template.PokedexSaidatemplate;
import com.prova.pokedex.template.PokedexTemplate;
import com.prova.pokedex.validator.exception.DadoApiExternaNotFound;
import com.prova.pokedex.validator.exception.IdException;
import com.prova.pokedex.validator.exception.RegraNegocioException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokedexValidatorTeste {

    @InjectMocks
    private PokedexValidator pokedexValidator;

    @Mock
    private PokedexService pokedexService;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates(PokedexTemplate.class.getPackage().getName());
        FixtureFactoryLoader.loadTemplates(PokedexSaidatemplate.class.getPackage().getName());
    }

    @Test
    public void validaIdTesteSucesso(){
        Integer id = 1;

        try {
            pokedexValidator.validaId(id,"Campo");
            System.out.println("OK");
        }catch (IdException idException){
            Assert.assertEquals("Id do(a) Campo Inválido!",idException.getMessage());
            System.out.println(idException.getMessage());
        }
    }



    @Test
    public void validaIdTesteErro(){
        Integer id = 0;

        try {
            pokedexValidator.validaId(id,"Campo");
            System.out.println("OK");
        }catch (IdException idException){
            Assert.assertEquals("Id do(a) Campo Inválido!",idException.getMessage());
            System.out.println(idException.getMessage());
        }
    }

    @Test
    public void validaRegistroSucesso() {
        PokedexEntrada pokedexEntrada = Fixture.from(PokedexEntrada.class).gimme(PokedexTemplate.VALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.listarPokemonPorEspecie(any(),any())).thenReturn(pokedexSaidaList);

        try {
            pokedexValidator.validaRegistro(pokedexEntrada);
            System.out.println("OK");
        }catch (RuntimeException runtimeException){
            Assert.assertEquals("Nivel do Pokemon Inválido",runtimeException.getMessage());
            System.out.println(runtimeException.getMessage());
        }
    }

    @Test
    public void validaRegistroIdPokemonErro() {
        PokedexEntrada pokedexEntrada = Fixture.from(PokedexEntrada.class).gimme(PokedexTemplate.IDPOKEMON_INVALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        try {
            pokedexValidator.validaRegistro(pokedexEntrada);
            System.out.println("OK");
        }catch (IdException idException){
            Assert.assertEquals("Id do(a) Pokemon Inválido!",idException.getMessage());
            System.out.println(idException.getMessage());
        }
    }

    @Test
    public void validaRegistroNivelPokemonErro() {
        PokedexEntrada pokedexEntrada = Fixture.from(PokedexEntrada.class).gimme(PokedexTemplate.NIVEL_INVALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        try {
            pokedexValidator.validaRegistro(pokedexEntrada);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Nivel do Pokemon Inválido",regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void validaRegistroIdLocalErro() {
        PokedexEntrada pokedexEntrada = Fixture.from(PokedexEntrada.class).gimme(PokedexTemplate.LOCAL_INVALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.listarPokemonPorEspecie(any(),any())).thenReturn(pokedexSaidaList);

        try {
            pokedexValidator.validaRegistro(pokedexEntrada);
            System.out.println("OK");
        }catch (IdException idException){
            Assert.assertEquals("Id do(a) Local Inválido!",idException.getMessage());
            System.out.println(idException.getMessage());
        }
    }

    @Test
    public void validaRegistroIdUsuarioErro() {
        PokedexEntrada pokedexEntrada = Fixture.from(PokedexEntrada.class).gimme(PokedexTemplate.USUARIO_INVALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.listarPokemonPorEspecie(any(),any())).thenReturn(pokedexSaidaList);

        try {
            pokedexValidator.validaRegistro(pokedexEntrada);
            System.out.println("OK");
        }catch (IdException idException){
            Assert.assertEquals("Id do(a) Usuario Inválido!",idException.getMessage());
            System.out.println(idException.getMessage());
        }
    }

    @Test
    public void validaRegistroQuantidadePokemonErro() {
        PokedexEntrada pokedexEntrada = Fixture.from(PokedexEntrada.class).gimme(PokedexTemplate.VALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.listarPokemonPorEspecie(any(),any())).thenReturn(pokedexSaidaList);

        try {
            pokedexValidator.validaRegistro(pokedexEntrada);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: O Usuario já possui o Limite desse Pokemon",regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void validaLibertacaoSucesso() {
        Integer idPokedex = 1;
        PokedexSaida pokedexSaida = Fixture.from(PokedexSaida.class).gimme(PokedexSaidatemplate.VALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.pegaUmRegistro(any())).thenReturn(pokedexSaida);
        when(pokedexService.listarPokemonPorEspecie(anyInt(), any())).thenReturn(pokedexSaidaList);
        when(pokedexService.registroExiste(any())).thenReturn(true);

        try{
            pokedexValidator.validaLibertacao(idPokedex);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Você não pode libertar esse Pokemon",regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void validaLibertacaoIdErro() {
        Integer idPokedex = 0;

        try{
            pokedexValidator.validaLibertacao(idPokedex);
            System.out.println("OK");
        }catch (IdException idException){
            Assert.assertEquals("Id do(a) Pokedex Inválido!",idException.getMessage());
            System.out.println(idException.getMessage());
        }
    }

    @Test
    public void validaLibertacaoPokemonUnicoErro() {
        Integer idPokedex = 1;
        PokedexSaida pokedexSaida = Fixture.from(PokedexSaida.class).gimme(PokedexSaidatemplate.VALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.pegaUmRegistro(any())).thenReturn(pokedexSaida);
        when(pokedexService.listarPokemonPorEspecie(anyInt(), any())).thenReturn(pokedexSaidaList);
        when(pokedexService.registroExiste(any())).thenReturn(true);

        try{
            pokedexValidator.validaLibertacao(idPokedex);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode libertar esse Pokemon",regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

    @Test
    public void validaLibertacaoPokemonNaoExisteErro() {
        Integer idPokedex = 1;

        when(pokedexService.registroExiste(any())).thenReturn(false);

        try{
            pokedexValidator.validaLibertacao(idPokedex);
            System.out.println("OK");
        }catch (DadoApiExternaNotFound dadoApiExternaNotFound){
            Assert.assertEquals("Registro não Existe não Encontrado(a)!",dadoApiExternaNotFound.getMessage());
            System.out.println(dadoApiExternaNotFound.getMessage());
        }
    }

    @Test
    public void validaLibertacaoPokemonEmTrocaErro() {
        Integer idPokedex = 1;
        PokedexSaida pokedexSaida = Fixture.from(PokedexSaida.class).gimme(PokedexSaidatemplate.INVALIDO);

        List<PokedexSaida> pokedexSaidaList = new ArrayList<>();
        pokedexSaidaList.add(new PokedexSaida());
        pokedexSaidaList.add(new PokedexSaida());

        when(pokedexService.pegaUmRegistro(any())).thenReturn(pokedexSaida);
        when(pokedexService.listarPokemonPorEspecie(anyInt(), any())).thenReturn(pokedexSaidaList);
        when(pokedexService.registroExiste(any())).thenReturn(true);

        try{
            pokedexValidator.validaLibertacao(idPokedex);
            System.out.println("OK");
        }catch (RegraNegocioException regraNegocioException){
            Assert.assertEquals("Erro: Você não pode libertar esse Pokemon",regraNegocioException.getMessage());
            System.out.println(regraNegocioException.getMessage());
        }
    }

}
