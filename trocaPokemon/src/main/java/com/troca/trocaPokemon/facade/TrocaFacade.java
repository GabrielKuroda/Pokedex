package com.troca.trocaPokemon.facade;

import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import com.troca.trocaPokemon.service.TrocaService;
import com.troca.trocaPokemon.validator.TrocaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrocaFacade {

    @Autowired
    private TrocaService trocaService;

    @Autowired
    private TrocaValidator trocaValidator;

    public TrocaSaida abrirNovaTroca(TrocaEntrada trocaEntrada) {
        trocaValidator.validaNovaTroca(trocaEntrada);
        return trocaService.abrirNovaTroca(trocaEntrada);
    }

    public List<TrocaSaida> listarTrocasAbertas() {
        return trocaService.listarTrocasAbertas();
    }

    public TrocaSaida realizarTroca(Integer idRegistro, Integer idTroca) {
        trocaValidator.validaRealizarTroca(idRegistro,idTroca);
        return trocaService.realizarTroca(idRegistro,idTroca);
    }
}
