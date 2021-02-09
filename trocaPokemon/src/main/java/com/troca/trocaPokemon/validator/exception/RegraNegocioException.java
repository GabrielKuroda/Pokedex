package com.troca.trocaPokemon.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super("Erro: "+ message);
    }

}
