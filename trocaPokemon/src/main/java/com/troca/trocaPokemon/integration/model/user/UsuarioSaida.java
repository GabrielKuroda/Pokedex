package com.troca.trocaPokemon.integration.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioSaida {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String nomeUsuario;
}
