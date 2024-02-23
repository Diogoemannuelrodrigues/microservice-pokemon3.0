package io.github.com.attack.controller;

import io.github.com.attack.entity.Move;
import io.github.com.attack.service.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/move")
public class MoveController {

    private final MoveService moveService;

    @GetMapping
    public List<Move> getMove(){
        return moveService.getMove();
    }

    @PostMapping
    public Move save(@RequestBody Move move){
        return move;
    }

    @GetMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public String startTheGame(){
        return moveService.startTheGame();
    }

    @GetMapping(value = "/adiciona/{nameMove}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean adicionaAtaque(@PathVariable String nameMove){
        return moveService.verifyIfPokemonCanToReceiveMove(nameMove);
    }

}
