package com.troca.trocaPokemon.integration.client;

import com.troca.trocaPokemon.integration.model.pokedex.PokedexRetorno;
import com.troca.trocaPokemon.integration.model.enums.StatusEnumPokedex;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "pokedex",url = "${pokedex.endpoint}")
public interface PokedexClient {

    @PutMapping("/pokemon/{idRegistro}/atualiza/usuario/{idUsuario}")
    void atualizaUsuario(@PathVariable(name = "idRegistro") Integer idPokedex,
                                @PathVariable(name = "idUsuario") Integer idUsuario);

    @GetMapping("/registro/{idPokedex}")
    PokedexRetorno pegaUmRegistro(@PathVariable(name = "idPokedex") Integer idPokedex);

    @PutMapping("/pokemon/emtroca/{idPokedex}")
    void atualizaStatus(@PathVariable(name = "idPokedex") Integer idPokedex, @RequestBody StatusEnumPokedex statusEnum);

    @GetMapping("/{idUsuario}/pokemon/{idPokemon}")
    List<PokedexRetorno> listarPokemonPorEspecie(@PathVariable(name = "idUsuario") Integer idUsuario,
                        @PathVariable(name = "idPokemon") Integer idPokemon);

}
