package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.service.MoveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/move")
@Tag(name = "Moves", description = "API responsável pelos movimentos dos Pokémons")
public class MoveController {

    private final MoveService service;

    public MoveController(MoveService service) {
        this.service = service;
    }

    @Operation(summary = "Inicia o jogo", description = "Responsável por iniciar a lógica principal do jogo")
    @ApiResponse(responseCode = "204", description = "Jogo iniciado com sucesso")
    @GetMapping
    public ResponseEntity<String> starTheGame() {
        service.startTheGame();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Verifica movimento", notes = "Verifica se um Pokémon pode aprender determinado movimento")
    @GetMapping(value = "/nameMove/{nameMove}/namePokemon/{namePokemon}")
    public ResponseEntity<Boolean> verificaSePokemonPodeReceberMovimento(
            @ApiParam(value = "Nome do Pokémon", example = "pikachu")
            @PathVariable String namePokemon,
            @ApiParam(value = "Nome do movimento", example = "thunderbolt")
            @PathVariable String nameMove) {
        Boolean podeAprender = service.podeAprenderOhMovimento(nameMove, namePokemon);
        return ResponseEntity.ok(podeAprender);
    }
}