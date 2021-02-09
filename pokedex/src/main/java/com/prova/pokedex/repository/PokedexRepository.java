package com.prova.pokedex.repository;

import com.prova.pokedex.model.entity.PokedexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokedexRepository extends JpaRepository<PokedexEntity,Integer> {

    Optional<List<PokedexEntity>> findAllByIdUsuario(Integer idUsuario);
    Optional<List<PokedexEntity>> findAllByIdPokemonAndIdUsuario(Integer idPokemon,Integer idUsuario);

}
