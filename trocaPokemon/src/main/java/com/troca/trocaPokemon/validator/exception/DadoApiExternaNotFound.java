package com.troca.trocaPokemon.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DadoApiExternaNotFound extends RuntimeException{

    public DadoApiExternaNotFound(String entidade) {
        super(entidade + " não Encontrado(a)!");
    }
}
