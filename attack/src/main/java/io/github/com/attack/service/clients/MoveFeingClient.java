package io.github.com.attack.service.clients;

import io.github.com.attack.entity.MoveRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-pokemon", url = "https://pokeapi.co/api/v2/move/")
public interface MoveFeingClient {

    @GetMapping(value = "/{id}")
    String getMove(@PathVariable("id") Integer id);

    @GetMapping(value = "/{id}")
    MoveRecord getMoveId(@PathVariable("id") Integer id);


}



