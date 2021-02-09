package com.troca.trocaPokemon.integration.service;

import com.troca.trocaPokemon.integration.client.PokedexClient;
import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.model.enums.StatusEnumPokedex;
import com.troca.trocaPokemon.mapper.TrocaMapper;
import com.troca.trocaPokemon.model.entity.TrocaEntity;
import com.troca.trocaPokemon.model.enums.StatusEnum;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import com.troca.trocaPokemon.validator.exception.DadoApiExternaNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokedexService {

    @Autowired
    private PokedexClient pokedexClient;

    public PokedexRetorno pegaUmregistro(Integer idRegistro){
        try {
            return pokedexClient.pegaUmRegistro(idRegistro);
        }catch (RuntimeException runtimeException){
            throw new DadoApiExternaNotFound("Registro não encontrado!");
        }

    }

    public void atualizaStatusPokedex(Integer idRegistro, StatusEnumPokedex statusEnumPokedex){
        pokedexClient.atualizaStatus(idRegistro,statusEnumPokedex);
    }

    public List<PokedexRetorno> pegaRegistros(Integer idRegistro){
        PokedexRetorno pokedexRetorno = pegaUmregistro(idRegistro);
        return pokedexClient.listarPokemonPorEspecie(pokedexRetorno.getUsuario().getId(),pokedexRetorno.getPokemon().getIdPokemon());
    }

    public List<PokedexRetorno> pegaRegistrosPorEspecie(Integer idUsuario,Integer idPokemon){
        return pokedexClient.listarPokemonPorEspecie(idUsuario,idPokemon);
    }

    public TrocaEntity realizaTroca(Integer idRegistro, TrocaSaida trocaEscolhida){
        PokedexRetorno trocaEnvio = pegaUmregistro(idRegistro);
        PokedexRetorno trocaRetorno = pegaUmregistro(trocaEscolhida.getPokemonTroca().getId());

        atualizaStatusPokedex(trocaEscolhida.getPokemonTroca().getId(), StatusEnumPokedex.EM_POSSE);

        pokedexClient.atualizaUsuario(trocaEnvio.getId(),trocaRetorno.getUsuario().getId());
        pokedexClient.atualizaUsuario(trocaRetorno.getId(),trocaEnvio.getUsuario().getId());

        trocaEscolhida.setStatusEnum(StatusEnum.FECHADA);

        return TrocaMapper.MAPPER.toEntity
                (trocaEscolhida, trocaEscolhida.getPokemonTroca().getId());
    }
}
