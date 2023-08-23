package br.com.microservice.pokemon.service.clients;

import br.com.microservice.pokemon.domain.Stone;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "stoneClient", url = "https://pokeapi.glitch.me/v1/evolution-stone")
public interface StoneFeignClient {

    @GetMapping(value = "/{name}")
    Stone getStoneByName(@PathVariable("name") String name);

    @GetMapping
    String getAllStones();
}
