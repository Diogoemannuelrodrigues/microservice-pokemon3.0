package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @PostMapping
    public Pokemon saveAll(@RequestBody String json){
        return service.convertPokemon(json);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<PokemonDTO> findByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.getPokemon(name));
    }

    @GetMapping
    public ResponseEntity<String> starTheGame(){
        return ResponseEntity.ok().body(service.starTheGame());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PokemonDTO>> findAllPokemons() {
        return ResponseEntity.ok().body(service.findAllPokemon());
    }

    @GetMapping(value = "evolutions/{name}")
    public ResponseEntity<Object> findByFamilyName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.getNextEvolucaoPokemonByGlitch(name));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Pokemon>> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/verificaEvolution/{treinador}/pokemon/{pokemonName}/stone/{stone}")
    public ResponseEntity<String> verifyIfCanBeToEvolve(@PathVariable String treinador,
                                                        @PathVariable String pokemonName,
                                                        @PathVariable String stone) {
        return ResponseEntity.ok().body(service.getToEvolvePokemonWithStone(treinador, pokemonName, stone));
    }

    @GetMapping(value = "/evoluir/{pokemonName}/teinador/{treinador}")
    public ResponseEntity<Pokemon> evlouirPokemon(@PathVariable String pokemonName,
                                                  @PathVariable String treinador) {
        return ResponseEntity.ok().body(service.getEvoluirPokemon(pokemonName, treinador));
    }

}
