package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @PostMapping
    public Pokemon saveAll(@RequestBody String json){
        return service.convertPokemon(json);
    }
    @GetMapping(value = "/{name}")
    public ResponseEntity<PokemonDTO> findByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.getPokemon(name));
    }

    @GetMapping
    public ResponseEntity<String> starTheGame(){
        return ResponseEntity.ok().body(service.starTheGame());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PokemonDTO>> findAllPokemons() throws IOException {
        return ResponseEntity.ok().body(service.findAllPokemon());
    }


}
