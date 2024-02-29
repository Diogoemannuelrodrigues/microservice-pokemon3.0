package br.com.microservice.pokemon.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "glitchClient", url = "${pokeapi.glitch}")
public interface GlitchfeignClient {

    @GetMapping(value = "pokemon/{name}")
    String getPokemonDTO(@PathVariable("name") String name);
}
