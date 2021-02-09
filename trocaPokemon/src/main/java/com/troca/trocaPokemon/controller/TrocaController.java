package com.troca.trocaPokemon.controller;

import com.troca.trocaPokemon.facade.TrocaFacade;
import com.troca.trocaPokemon.model.entrada.TrocaEntrada;
import com.troca.trocaPokemon.model.saida.TrocaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/troca")
public class TrocaController {

    @Autowired
    private TrocaFacade trocaFacade;

    @PostMapping("/abrir")
    public TrocaSaida abrirNovaTroca(@RequestBody TrocaEntrada trocaEntrada){
        return trocaFacade.abrirNovaTroca(trocaEntrada);
    }

    @GetMapping("/abertas")
    public List<TrocaSaida> listarTrocasAbertas(){
        return trocaFacade.listarTrocasAbertas();
    }

    @PostMapping("/{idRegistro}/receber/{idTroca}")
    public TrocaSaida realizarTroca(@PathVariable(name = "idRegistro") Integer idRegistro,
                                    @PathVariable(name = "idTroca") Integer idTroca){
        return trocaFacade.realizarTroca(idRegistro,idTroca);
    }

}
