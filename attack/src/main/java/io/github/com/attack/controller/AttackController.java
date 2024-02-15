package io.github.com.attack.controller;

import io.github.com.attack.entity.Move;
import io.github.com.attack.service.AttackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/attack")
public class AttackController {
    private AttackService attackService;

    public AttackController(AttackService attackService) {
        this.attackService = attackService;
    }

    @GetMapping
    public List<Move> findAll(){
        return attackService.findAll();
    }

    @PostMapping
    public List<Move> saveAll(@RequestBody List<Move> attacks){
        return attackService.saveAll(attacks);
    }

    @GetMapping("type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Move> findByCategory(@PathVariable String type){
        return attackService.findAttacksByType(type);
    }

    @GetMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public String startTheGame(){
        return attackService.starTheGame();
    }

}
