package com.prova.pokedex.integration.userapi.client;

import com.prova.pokedex.integration.userapi.model.UsuarioRetorno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario",url = "${usuario.endpoint}")
public interface UsuarioClient {

    @GetMapping("/{id}")
    UsuarioRetorno getUsuario(@PathVariable(name = "id")Integer idUsuario);

}
