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
    public ResponseEntity<Treinador> save(@RequestBody TreinadorDto treinadorDto) {
        return ResponseEntity.ok().body(service.save(treinadorDto));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Treinador> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Treinador>> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/adiciona/{treinador}/")
    public ResponseEntity<Treinador> findTreinadorandPOkemon(@PathVariable String treinador) {
        return ResponseEntity.ok().body(service.adicionaPokemonParaTreinador(treinador));
    }

}
