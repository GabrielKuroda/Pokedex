package com.prova.pokedex.controller;

import com.prova.pokedex.enums.StatusEnum;
import com.prova.pokedex.facade.PokedexFacade;
import com.prova.pokedex.model.entrada.PokedexEntrada;
import com.prova.pokedex.model.saida.PokedexSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {

    @Autowired
    private PokedexFacade pokedexFacade;

    @PostMapping
    public ResponseEntity<PokedexSaida> salvarRegistro(@RequestBody PokedexEntrada pokedexEntrada){
        PokedexSaida pokedexSaida = pokedexFacade.salvarRegistro(pokedexEntrada);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pokedexSaida.getId()).toUri();
        return ResponseEntity.created(uri).body(pokedexSaida);
    }

    @GetMapping("/{idUsuario}/pokemon")
    public ResponseEntity<List<PokedexSaida>> listarPokemon(@PathVariable(name = "idUsuario") Integer idUsuario){
        return ResponseEntity.ok().body(pokedexFacade.listarPokemon(idUsuario));
    }

    @GetMapping("/{idUsuario}/pokemon/{idPokemon}")
    public ResponseEntity<List<PokedexSaida>> listarPokemonPorEspecie(@PathVariable(name = "idUsuario") Integer idUsuario,
                                                 @PathVariable(name = "idPokemon") Integer idPokemon){
        return ResponseEntity.ok().body(pokedexFacade.listarPokemonPorEspecie(idUsuario,idPokemon));
    }

    @GetMapping("/registro/{idPokedex}")
    public ResponseEntity<PokedexSaida> pegaUmRegistro(@PathVariable(name = "idPokedex") Integer idPokedex){
        return ResponseEntity.ok().body(pokedexFacade.pegaUmRegistro(idPokedex));
    }

    @DeleteMapping("/pokemon/{idPokedex}/libertar")
    public ResponseEntity libertarPokemon(@PathVariable(name = "idPokedex") Integer idPokedex){
        pokedexFacade.libertarPokemon(idPokedex);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/pokemon/emtroca/{idPokedex}")
    public void atualizaStatus(@PathVariable(name = "idPokedex") Integer idPokedex, @RequestBody StatusEnum statusEnum){
        pokedexFacade.atualizaStatus(idPokedex,statusEnum);
    }

    @PutMapping("/pokemon/{idRegistro}/atualiza/usuario/{idUsuario}")
    public void atualizaUsuario(@PathVariable(name = "idRegistro") Integer idPokedex,
                                @PathVariable(name = "idUsuario") Integer idUsuario){
        pokedexFacade.atualizaUsuario(idPokedex,idUsuario);
    }



}
