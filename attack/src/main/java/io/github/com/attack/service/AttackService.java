package io.github.com.attack.service;


import io.github.com.attack.config.ConvertDados;
import io.github.com.attack.entity.Move;
import io.github.com.attack.repository.AttackRepository;
import io.github.com.attack.service.clients.MoveFeingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttackService {

    private final AttackRepository attackRepository;
    private final ConvertDados convertDados;
    private final MoveFeingClient moveFeingClient;


    public Move save(Move attack) {
        return attackRepository.save(attack);
    }

    public Move convertPokemon(String json) {
        var move = convertDados.obterDados(json, Move.class);
        return attackRepository.save(move);
    }

    public String starTheGame() {
        for (int i = 1; i <= 919; i++) {
            var move = moveFeingClient.getMove(i);
            var movedConverted = convertDados.obterDados(move, Move.class);
            log.info(String.valueOf(movedConverted));
        }

        return "the game can start now";
    }

    public List<Move> findAll(){
        return attackRepository.findAll();
    }
    public List<Move> saveAll(List<Move> atacks){
        return attackRepository.saveAll(atacks);
    }
    public Move findByName(String movimento) {
        return attackRepository.findByName(movimento);
    }
    public List<Move> findAttacksByType(String type){
        return attackRepository.findByType(type);
    }
}
