package br.com.microservice.attack.application.service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-pokemon", url = "https://pokeapi.co/api/v2/move/")
public interface MoveFeingClient {

    @GetMapping(value = "/{id}")
    String getMove(@PathVariable("id") Integer id);
}