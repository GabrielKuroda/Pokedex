package com.prova.pokedex.integration.userapi.service;

import com.prova.pokedex.integration.userapi.model.UsuarioRetorno;
import com.prova.pokedex.validator.exception.DadoApiExternaNotFound;
import com.prova.pokedex.integration.userapi.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioClient usuarioClient;

    public UsuarioRetorno getUsuario(Integer idUsuario){
        try{
            return usuarioClient.getUsuario(idUsuario);
        }catch (RuntimeException ex){
            throw new DadoApiExternaNotFound("Usuario");
        }

    }

}
