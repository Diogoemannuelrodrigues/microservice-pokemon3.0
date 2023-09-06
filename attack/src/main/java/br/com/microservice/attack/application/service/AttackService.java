package br.com.microservice.attack.application.service;

import br.com.microservice.attack.application.core.domain.Attack;
import br.com.microservice.attack.application.repository.AttackRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AttackService {

    private final AttackRepository attackRepository;

    public Attack save(Attack attack) {
        return attackRepository.save(attack);
    }

    public List<Attack> findAll(){
        return attackRepository.findAll();
    }
    public List<Attack> saveAll(List<Attack> atacks){
        return attackRepository.saveAll(atacks);
    }

    public Attack findByName(String movimento) {
        return attackRepository.findByName(movimento);
    }

    public List<Attack> findAttacksByType(String tipo){
        return attackRepository.findByType(tipo);
    }
}
