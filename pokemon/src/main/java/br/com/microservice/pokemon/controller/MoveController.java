package br.com.microservice.pokemon.controller;

import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.domain.records.MoveResponse;
import br.com.microservice.pokemon.service.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/move")
public class MoveController {

    private final MoveService moveService;

    @GetMapping
    public ResponseEntity<Page<MoveResponse>> findAllMoves(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "3") Integer size,
                                                           @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(moveService.movesPages(page, size, orderBy, direction));
    }

}
