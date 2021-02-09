package com.prova.pokedex.integration.pokeapi.client.local;

import com.prova.pokedex.integration.pokeapi.model.local.LocalRetorno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "local",url = "${local.endpoint}")
public interface LocalClient {

    @GetMapping("/{idLocal}")
    LocalRetorno getLocal(@PathVariable(name = "idLocal")Integer idLocal);

}
