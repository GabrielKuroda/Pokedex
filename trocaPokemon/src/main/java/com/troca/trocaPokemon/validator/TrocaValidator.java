package com.troca.trocaPokemon.validator;

import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.service.PokedexService;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import com.troca.trocaPokemon.service.TrocaService;
import com.troca.trocaPokemon.validator.exception.IdException;
import com.troca.trocaPokemon.validator.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrocaValidator {

    @Autowired
    private PokedexService pokedexService;

    @Autowired
    private TrocaService trocaService;

    public void validaNovaTroca(TrocaEntrada trocaEntrada) {
        validaId(trocaEntrada.getPokemonTroca(),"Pokemon");

        if (pokedexService.pegaRegistros(trocaEntrada.getPokemonTroca()).size() == 1){
            throw new RegraNegocioException("Você não pode trocar esse Pokemon, MOTIVO: Ele é unico");
        }

        if(trocaService.trocaExiste(trocaEntrada.getPokemonTroca())){
            throw new RegraNegocioException("Troca já cadastrada!");
        }

    }

    public void validaId(Integer id,String campo){
        if(id <= 0){
            throw new IdException(campo);
        }
    }

    public void validaRealizarTroca(Integer idRegistro, Integer idTroca) {
        validaId(idRegistro,"Registro");
        validaId(idTroca,"Troca");

        List<PokedexRetorno> pokedexRetornos = pokedexService.pegaRegistros(idRegistro);

        PokedexRetorno pokedexRetorno = pokedexRetornos.stream()
                .filter(pokedexRetornoDaLista -> pokedexRetornoDaLista.getId().equals(idRegistro))
                .collect(Collectors.toList()).get(0);

        TrocaSaida trocaSaida = trocaService.pegaUmaTroca(idTroca);

        int quantidadePokemonIguaisADaTrocaSolicitada =
                pokedexService.pegaRegistrosPorEspecie(pokedexRetorno.getUsuario().getId(),
                        trocaSaida.getPokemonTroca().getPokemon().getIdPokemon()).size();

        int quantidadePokemonIguaisQueOCriadotDaTrocaTem = pokedexService.pegaRegistrosPorEspecie
                (trocaSaida.getPokemonTroca().getUsuario().getId(),
                        pokedexRetorno.getPokemon().getIdPokemon()).size();

        verificaSePokemonEUnico(pokedexRetornos.size());

        verificaLimitePokemon(quantidadePokemonIguaisADaTrocaSolicitada,quantidadePokemonIguaisQueOCriadotDaTrocaTem);

        verificaTrocaConsigoMesmo(pokedexRetorno,trocaSaida);

        verificaPokemonIgual(pokedexRetorno,trocaSaida);

    }

    public void verificaLimitePokemon(Integer quantidadePokemonIguaisADaTrocaSolicitada,Integer quantidadePokemonIguaisQueOCriadotDaTrocaTem){
        if(quantidadePokemonIguaisADaTrocaSolicitada >= 3){
            throw new RegraNegocioException("Você já possui o Limite desse Pokemon");
        }

        if(quantidadePokemonIguaisQueOCriadotDaTrocaTem >= 3){
            throw new RegraNegocioException("O outro treinador já possui o Limite desse Pokemon");
        }
    }

    public void verificaTrocaConsigoMesmo(PokedexRetorno pokedexRetorno,TrocaSaida trocaSaida){
        Integer idUsuario = pokedexRetorno.getUsuario().getId();
        Integer id = trocaSaida.getPokemonTroca().getUsuario().getId();
        if(idUsuario.equals(id)){
            throw new RegraNegocioException("Você não pode trocar com você mesmo!");
        }
    }

    public void verificaSePokemonEUnico(Integer quantidadePokemon){
        if (quantidadePokemon == 1){
            throw new RegraNegocioException("Você não pode trocar esse Pokemon, MOTIVO: Ele é o unico");
        }
    }

    public void verificaPokemonIgual(PokedexRetorno pokedexRetorno,TrocaSaida trocaSaida){
        Integer idPokemon = pokedexRetorno.getPokemon().getIdPokemon();
        Integer idPokemonTroca = trocaSaida.getPokemonTroca().getPokemon().getIdPokemon();
        if (idPokemon.equals(idPokemonTroca)){
            throw new RegraNegocioException("Você não pode trocar pokemon semelhantes!");
        }
    }
}
