package com.troca.trocaPokemon.model.entity;

import com.troca.trocaPokemon.model.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "troca")
public class TrocaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pokemonTroca")
    private Integer pokemonTroca;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "status")
    private StatusEnum statusEnum;

}
