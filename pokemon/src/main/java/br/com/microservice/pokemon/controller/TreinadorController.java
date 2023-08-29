package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.domain.Treinador;
import br.com.microservice.pokemon.domain.TreinadorDto;
import br.com.microservice.pokemon.service.TreinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/treinador")
public class TreinadorController {

    @Autowired
    private TreinadorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Treinador> save(@RequestBody TreinadorDto treinadorDto){
        return ResponseEntity.ok().body(service.save(treinadorDto));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Treinador> findByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @GetMapping(value = "treinador/{id}")
    public ResponseEntity<Optional<Treinador>> findById(@PathVariable String id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
