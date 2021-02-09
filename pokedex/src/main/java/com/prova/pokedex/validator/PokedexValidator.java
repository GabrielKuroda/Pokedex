package com.prova.pokedex.validator;

import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.model.entrada.PokedexEntrada;
import com.prova.pokedex.model.saida.PokedexSaida;
import com.prova.pokedex.service.PokedexService;
import com.prova.pokedex.validator.exception.DadoApiExternaNotFound;
import com.prova.pokedex.validator.exception.IdException;
import com.prova.pokedex.validator.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokedexValidator {

    @Autowired
    private PokedexService pokedexService;

    public void validaRegistro(PokedexEntrada pokedexEntrada) {
        validaPokemon(pokedexEntrada.getIdPokemon(),pokedexEntrada.getNivelPokemon());
        validaQuantidadePokemon(pokedexEntrada.getIdUsuario(),pokedexEntrada.getIdPokemon());
        validaId(pokedexEntrada.getIdLocal(), "Local");
        validaId(pokedexEntrada.getIdUsuario(), "Usuario");
    }

    public void validaId(Integer id,String campo){
        if(id <= 0){
            throw new IdException(campo);
        }
    }

    private void validaPokemon(Integer idPokemon,Integer nivelPokemon){
        validaId(idPokemon,"Pokemon");
        if(nivelPokemon < 5 || nivelPokemon > 100){
            throw new RegraNegocioException("Nivel do Pokemon Inválido");
        }

    }

    private void validaQuantidadePokemon(Integer idUsuario,Integer idPokemon){
        if(pokedexService.listarPokemonPorEspecie(idPokemon,idUsuario).size() >=3){
            throw new RegraNegocioException("O Usuario já possui o Limite desse Pokemon");
        }
    }

    public void validaLibertacao(Integer idPokedex) {
        validaId(idPokedex,"Pokedex");
        if(!pokedexService.registroExiste(idPokedex)){
            throw new DadoApiExternaNotFound("Registro não Existe");
        }
        PokedexSaida pokedexSaida = pokedexService.pegaUmRegistro(idPokedex);
        if (pokedexService.listarPokemonPorEspecie(pokedexSaida.getPokemon().getIdPokemon(),
                pokedexSaida.getUsuario().getIdUsuario()).size() == 1 ||
                    pokedexSaida.getStatusEnum().equals(StatusEnum.EM_TROCA)){
            throw new RegraNegocioException("Você não pode libertar esse Pokemon");
        }

    }

    public void validaPegaUmRegistro(Integer idPokedex) {
        validaId(idPokedex,"Pokedex");
        if(!pokedexService.registroExiste(idPokedex)){
            throw new DadoApiExternaNotFound("Registro não Existe");
        }
    }
}
