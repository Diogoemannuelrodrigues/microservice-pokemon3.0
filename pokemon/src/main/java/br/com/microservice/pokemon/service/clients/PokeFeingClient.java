package br.com.microservice.pokemon.service.clients;

import br.com.microservice.pokemon.domain.PokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-pokemon", url = "https://pokeapi.co/api/v2/pokemon/")
public interface PokeFeingClient {

    @GetMapping(value = "/{name}")
    PokemonDTO getPkemonDTO(@PathVariable("name") String name);

    @GetMapping(value = "/{id}")
    String getPkemonById(@PathVariable("id") Integer id);
}
