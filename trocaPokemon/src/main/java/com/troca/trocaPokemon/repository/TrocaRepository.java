package com.troca.trocaPokemon.repository;

import com.troca.trocaPokemon.model.entity.TrocaEntity;
import com.troca.trocaPokemon.model.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrocaRepository extends JpaRepository<TrocaEntity,Integer> {

    List<TrocaEntity> findAllByStatusEnum(StatusEnum statusEnum);
    List<TrocaEntity> findAllByPokemonTroca(Integer idPokemon);

}
