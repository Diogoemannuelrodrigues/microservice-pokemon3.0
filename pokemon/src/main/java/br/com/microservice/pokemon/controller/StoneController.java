package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.domain.Stone;
import br.com.microservice.pokemon.service.StoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/stones")
public class StoneController {

    @Autowired
    private StoneService service;

    @GetMapping(value = "/all")
    public ResponseEntity<String> findAllStones(){
        return ResponseEntity.ok().body(service.getAllStones());
    }


    @GetMapping(value = "/{name}")
    public ResponseEntity<Stone> findStoneByName(@PathVariable String name) throws IOException {
        return ResponseEntity.ok().body(service.getStoneByName(name));
    }


}
