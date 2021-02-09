package com.prova.pokedex.service;

import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.integration.pokeapi.model.local.LocalRetorno;
import com.prova.pokedex.integration.pokeapi.model.local.RegiaoRetorno;
import com.prova.pokedex.integration.pokeapi.model.pokemon.PokemonRetorno;
import com.prova.pokedex.integration.pokeapi.service.PokeAPIService;
import com.prova.pokedex.integration.userapi.model.UsuarioRetorno;
import com.prova.pokedex.integration.userapi.service.UsuarioService;
import com.prova.pokedex.model.entity.PokedexEntity;
import com.prova.pokedex.model.entrada.PokedexEntrada;
import com.prova.pokedex.model.mapper.PokedexMapper;
import com.prova.pokedex.model.saida.PokedexSaida;
import com.prova.pokedex.repository.PokedexRepository;
import com.prova.pokedex.validator.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokedexService {

    @Autowired
    private PokedexRepository pokedexRepository;

    @Autowired
    private PokeAPIService pokeAPIService;

    @Autowired
    private UsuarioService usuarioService;

    public PokedexSaida salvarRegistro(PokedexEntrada pokedexEntrada) {

        PokedexEntity pokedexEntity = pokedexRepository.save(PokedexMapper.MAPPER.toEntity(pokedexEntrada, LocalDate.now(), StatusEnum.EM_POSSE));

        PokemonRetorno pokemon = pokeAPIService.getPokemon(pokedexEntity.getIdPokemon());
        LocalRetorno local = pokeAPIService.getLocal(pokedexEntity.getIdLocal());
        if(local.getRegiao() == null){
            RegiaoRetorno regiaoRetorno = new RegiaoRetorno();
            regiaoRetorno.setNomeRegiao("Não Registrada");
            local.setRegiao(regiaoRetorno);
        }
        UsuarioRetorno usuario = usuarioService.getUsuario(pokedexEntity.getIdUsuario());

        return PokedexMapper.MAPPER.toSaida(pokedexEntity, pokemon, local, usuario);
    }

    public List<PokedexSaida> listarPokemon(Integer idUsuario) {
        List<PokedexSaida> saidaList = new ArrayList<>();
        pokedexRepository.findAllByIdUsuario(idUsuario)
                .ifPresent(pokedexEntities -> pokedexEntities.forEach(pokedexEntity -> {

                            PokemonRetorno pokemon = pokeAPIService.getPokemon(pokedexEntity.getIdPokemon());
                            LocalRetorno local = pokeAPIService.getLocal(pokedexEntity.getIdLocal());
                            UsuarioRetorno usuario = usuarioService.getUsuario(pokedexEntity.getIdUsuario());

                            saidaList.add(PokedexMapper.MAPPER.toSaida(pokedexEntity, pokemon, local, usuario));
                        })
                );

        return saidaList;
    }

    public List<PokedexSaida> listarPokemonPorEspecie(Integer idPokemon, Integer idUsuario) {
        List<PokedexSaida> saidaList = new ArrayList<>();
        pokedexRepository.findAllByIdPokemonAndIdUsuario(idPokemon, idUsuario)
                .ifPresent(pokedexEntities -> pokedexEntities.forEach(pokedexEntity -> {

                    PokemonRetorno pokemon = pokeAPIService.getPokemon(pokedexEntity.getIdPokemon());
                    LocalRetorno local = pokeAPIService.getLocal(pokedexEntity.getIdLocal());
                    UsuarioRetorno usuario = usuarioService.getUsuario(pokedexEntity.getIdUsuario());

                     saidaList.add(PokedexMapper.MAPPER.toSaida(pokedexEntity, pokemon, local, usuario));
                    })
                );
        return saidaList;
    }

    public void libertarPokemon(Integer idPokedex) {
        try {
            pokedexRepository.deleteById(idPokedex);
        } catch (RuntimeException ex) {
            throw new RegraNegocioException("Pokemon não encontrado!");
        }
    }

    public PokedexSaida pegaUmRegistro(Integer idPokedex) {
        PokedexEntity pokedexEntity = pokedexRepository.getOne(idPokedex);

        PokemonRetorno pokemon = pokeAPIService.getPokemon(pokedexEntity.getIdPokemon());
        LocalRetorno local = pokeAPIService.getLocal(pokedexEntity.getIdLocal());
        UsuarioRetorno usuario = usuarioService.getUsuario(pokedexEntity.getIdUsuario());

        return PokedexMapper.MAPPER.toSaida(pokedexEntity, pokemon, local, usuario);

    }

    public void atualizaStatus(Integer idPokedex,StatusEnum statusEnum) {
        PokedexEntity pokedexEntity = pokedexRepository.getOne(idPokedex);
        pokedexEntity.setStatusEnum(statusEnum);
        pokedexRepository.save(pokedexEntity);
    }

    public void atualizaUsuario(Integer idPokedex, Integer idUsuario) {
        PokedexSaida pokedexSaida = pegaUmRegistro(idPokedex);
        PokedexEntity pokedexEntity = PokedexMapper.MAPPER.toEntity(pokedexSaida,
                pokedexSaida.getPokemon().getIdPokemon(),
                pokedexSaida.getLocal().getIdLocal(),
                pokedexSaida.getUsuario().getIdUsuario());
        pokedexEntity.setIdUsuario(idUsuario);
        pokedexRepository.save(pokedexEntity);
    }

    public boolean registroExiste(Integer idPokedex){
        try {
            pegaUmRegistro(idPokedex);
            return true;
        }catch (RuntimeException runtimeException){
            return false;
        }
    }
}
