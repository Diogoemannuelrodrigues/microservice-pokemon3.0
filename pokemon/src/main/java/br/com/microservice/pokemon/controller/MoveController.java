package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.service.MoveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/move")
public class MoveController {

    private final MoveService service;

    public MoveController(MoveService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> starTheGame() {
        service.startTheGame();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/nameMove/{nameMove}/namePokemon/{namePokemon}")
    public ResponseEntity<?> verificaSePokemonPodeReceberMovimento(
                                                                   @PathVariable String namePokemon,
                                                                   @PathVariable String nameMove) {
        Boolean podeAprender = this.service.podeAprenderOhMovimento(nameMove, namePokemon);
        return ResponseEntity.ok(podeAprender);
    }
}