package br.com.microservice.pokemon.service.clients;

import br.com.microservice.pokemon.domain.Stone;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stoneClient", url = "${stoneapi.url}")
public interface StoneFeignClient {

    @GetMapping(value = "evolution-stone/{name}")
    Stone getStoneByName(@PathVariable("name") String name);

    @GetMapping("/evolution-stone")
    String getAllStones();
}
