package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.service.PokemonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "evolutions/{name}")
    public ResponseEntity<Object> findByFamilyName(@PathVariable String name) throws JsonProcessingException {
        return ResponseEntity.ok().body(service.getEvolucaoPokemonByGlitch(name));
    }

    @GetMapping(value = "pokemon/{id}")
    public ResponseEntity<Optional<Pokemon>> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }


}
