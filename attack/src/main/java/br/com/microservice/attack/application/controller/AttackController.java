package br.com.microservice.attack.application.controller;

import br.com.microservice.attack.application.core.domain.Attack;
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
    @PostMapping
    public Attack saveAttack(@RequestBody Attack attack){
        return attackService.save(attack);
    }

    @GetMapping("/all")
    public List<Attack> findAll(){
        return attackService.findAll();
    }

    @PostMapping("/all")
    public List<Attack> saveAll(@RequestBody List<Attack> attacks){
        return attackService.saveAll(attacks);
    }

    @GetMapping("type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Attack> findByCategory(@PathVariable String type){
        return attackService.findAttacksByType(type);
    }

}
