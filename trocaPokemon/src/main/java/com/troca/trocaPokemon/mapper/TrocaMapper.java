package com.troca.trocaPokemon.mapper;

import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.model.entity.TrocaEntity;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.enums.StatusEnum;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Mapper
public interface TrocaMapper {

    TrocaMapper MAPPER = Mappers.getMapper(TrocaMapper.class);

    TrocaEntity toEntity(TrocaEntrada trocaEntrada, LocalDate data, StatusEnum statusEnum);

    @Mapping(target = "pokemonTroca",source = "pokemonTroca")
    @Mapping(target = "id",source = "trocaEntity.id")
    TrocaSaida toSaida(TrocaEntity trocaEntity, PokedexRetorno pokemonTroca);

    @Mapping(target = "pokemonTroca",source = "pokemonTroca")
    TrocaEntity toEntity(TrocaSaida trocaSaida,Integer pokemonTroca);

}
