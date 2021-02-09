package com.prova.pokedex.model.entity;

import com.prova.pokedex.enums.PokeBolasEnum;
import com.prova.pokedex.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "POKEDEX_REGISTERS")
public class PokedexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pokemon")
    private Integer idPokemon;

    @Column(name = "nivel")
    private Integer nivelPokemon;

    @Column(name = "data")
    private LocalDate dataCaptura;

    @Column(name = "local")
    private Integer idLocal;

    @Column(name = "treinador")
    private Integer idUsuario;

    @Column(name = "tipoPokebola")
    private PokeBolasEnum pokeBolasEnum;

    @Column(name = "status")
    private StatusEnum statusEnum;

}
