package com.troca.trocaPokemon.service;

import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.model.enums.StatusEnumPokedex;
import com.troca.trocaPokemon.integration.service.PokedexService;
import com.troca.trocaPokemon.mapper.TrocaMapper;
import com.troca.trocaPokemon.model.entity.TrocaEntity;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.enums.StatusEnum;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import com.troca.trocaPokemon.repository.TrocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrocaService {

    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private PokedexService pokedexService;

    public TrocaSaida abrirNovaTroca(TrocaEntrada trocaEntrada) {
        TrocaEntity save = trocaRepository.save(TrocaMapper.MAPPER.toEntity(trocaEntrada, LocalDate.now(), StatusEnum.ABERTA));
        PokedexRetorno pokedexRetorno = pokedexService.pegaUmregistro(trocaEntrada.getPokemonTroca());
        pokedexService.atualizaStatusPokedex(trocaEntrada.getPokemonTroca(), StatusEnumPokedex.EM_TROCA);
        return TrocaMapper.MAPPER.toSaida(save,pokedexRetorno);
    }

    public List<TrocaSaida> listarTrocasAbertas() {
        List<TrocaSaida> trocaSaidas = new ArrayList<>();
        trocaRepository.findAllByStatusEnum(StatusEnum.ABERTA).forEach(trocaEntity -> {
            PokedexRetorno pokedexRetorno = pokedexService.pegaUmregistro(trocaEntity.getPokemonTroca());
            trocaSaidas.add(TrocaMapper.MAPPER.toSaida(trocaEntity,pokedexRetorno));
        });
        return trocaSaidas;
    }

    public TrocaSaida pegaUmaTroca(Integer idTroca) {
        TrocaEntity trocaEntity = trocaRepository.getOne(idTroca);
        PokedexRetorno pokedexRetorno = pokedexService.pegaUmregistro(trocaEntity.getPokemonTroca());
        return TrocaMapper.MAPPER.toSaida(trocaEntity,pokedexRetorno);
    }

    public boolean trocaExiste(Integer idTroca){
        return !trocaRepository.findAllByPokemonTroca(idTroca).isEmpty();
    }

    public TrocaSaida realizarTroca(Integer idRegistro, Integer idTroca) {
        TrocaSaida trocaEscolhida = pegaUmaTroca(idTroca);
        PokedexRetorno pokedexRetorno = pokedexService.pegaUmregistro(idRegistro);

        TrocaEntity trocaEntity = pokedexService.realizaTroca(pokedexRetorno.getId(), trocaEscolhida);

        return TrocaMapper.MAPPER.toSaida(trocaRepository.save(trocaEntity),pokedexService.pegaUmregistro(trocaEntity.getPokemonTroca()));
    }
}
