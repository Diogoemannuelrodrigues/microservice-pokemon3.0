package br.com.microservice.attack.application.controller;

import br.com.microservice.attack.application.core.domain.Move;
import br.com.microservice.attack.application.service.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/attack")
public class AttackController {
    @Autowired
    private AttackService attackService;

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

}
