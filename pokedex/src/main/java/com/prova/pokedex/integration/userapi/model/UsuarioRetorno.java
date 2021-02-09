package com.prova.pokedex.integration.userapi.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioRetorno {

    @JsonProperty("id")
    private Integer idUsuario;

    @JsonProperty("name")
    private String nomeUsuario;

}
