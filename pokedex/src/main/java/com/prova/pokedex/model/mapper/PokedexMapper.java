package com.prova.pokedex.model.mapper;

import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.integration.pokeapi.model.local.LocalRetorno;
import com.prova.pokedex.integration.pokeapi.model.pokemon.PokemonRetorno;
import com.prova.pokedex.integration.userapi.model.UsuarioRetorno;
import com.prova.pokedex.model.saida.PokedexSaida;
import com.prova.pokedex.model.entity.PokedexEntity;
import com.prova.pokedex.model.entrada.PokedexEntrada;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Mapper
public interface PokedexMapper {

    PokedexMapper MAPPER = Mappers.getMapper(PokedexMapper.class);

    PokedexEntity toEntity(PokedexEntrada pokedexEntrada, LocalDate dataCaptura, StatusEnum statusEnum);

    PokedexSaida toSaida(PokedexEntity pokedexEntity, PokemonRetorno pokemon, LocalRetorno local, UsuarioRetorno usuario);

    PokedexEntity toEntity(PokedexSaida pokedexSaida,Integer idPokemon, Integer idLocal, Integer idUsuario);

}
