package br.com.microservice.pokemon.service.clients;

import br.com.microservice.pokemon.domain.PokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "pokemonClient", url = "${pokeapi.url}")
public interface PokeFeingClient {

    @GetMapping(value = "pokemon/{name}")
    PokemonDTO getPkemonDTO(@PathVariable("name") String name);

    @GetMapping(value = "pokemon/{id}")
    String getPkemonById(@PathVariable("id") Integer id);
}
