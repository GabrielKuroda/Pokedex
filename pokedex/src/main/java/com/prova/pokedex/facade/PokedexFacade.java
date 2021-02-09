package com.prova.pokedex.facade;

import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.model.saida.PokedexSaida;
import com.prova.pokedex.model.entrada.PokedexEntrada;
import com.prova.pokedex.service.PokedexService;
import com.prova.pokedex.validator.PokedexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokedexFacade {

    @Autowired
    private PokedexService pokedexService;

    @Autowired
    private PokedexValidator pokedexValidator;

    public PokedexSaida salvarRegistro(PokedexEntrada pokedexEntrada) {
        pokedexValidator.validaRegistro(pokedexEntrada);
        return pokedexService.salvarRegistro(pokedexEntrada);
    }

    public List<PokedexSaida> listarPokemon(Integer idUsuario) {
        pokedexValidator.validaId(idUsuario,"Usuario");
        return pokedexService.listarPokemon(idUsuario);
    }

    public void libertarPokemon(Integer idPokedex) {
        pokedexValidator.validaLibertacao(idPokedex);
        pokedexService.libertarPokemon(idPokedex);
    }

    public List<PokedexSaida> listarPokemonPorEspecie(Integer idUsuario, Integer idPokemon) {
        pokedexValidator.validaId(idUsuario,"Usuario");
        pokedexValidator.validaId(idPokemon,"Pokemon");
        return pokedexService.listarPokemonPorEspecie(idPokemon,idUsuario);
    }

    public PokedexSaida pegaUmRegistro(Integer idPokedex) {
        pokedexValidator.validaPegaUmRegistro(idPokedex);
        return pokedexService.pegaUmRegistro(idPokedex);
    }

    public void atualizaStatus(Integer idPokedex, StatusEnum statusEnum) {
        pokedexValidator.validaId(idPokedex,"Pokedex");
        pokedexService.atualizaStatus(idPokedex,statusEnum);
    }

    public void atualizaUsuario(Integer idPokedex, Integer idUsuario) {
        pokedexValidator.validaId(idPokedex,"Pokedex");
        pokedexValidator.validaId(idUsuario,"Usuario");
        pokedexService.atualizaUsuario(idPokedex,idUsuario);
    }
}
