package io.github.com.attack.service;

import io.github.com.attack.entity.Move;
import io.github.com.attack.entity.MoveRecord;
import io.github.com.attack.repository.MoveRepository;
import io.github.com.attack.service.clients.MoveFeingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoveService {

    private final MoveRepository moveRepository;
    private final ConverterDados converterDados;
    private final MoveFeingClient moveFeingClient;

    public List<Move> getMove() {
        return moveRepository
                .findAll()
                .stream()
                .toList();
    }

    //TODO - Create a job for to make this.
    public String startTheGame(){

        for (int i = 1; i<2; i++){
            var move = moveFeingClient.getMove(i);
            var moveConverted = converterDados.obterDados(move, Move.class);
            moveRepository.save(moveConverted);
        }

        return "Stating the game";
    }

    public Boolean verifyIfPokemonCanToReceiveMove(String nameMove){
        var move = moveRepository.findByName(nameMove);
        var jsonMove = moveFeingClient.getMove(move.getId());
        var listOfPokemonThatCanBeReceiveThisMmove = converterDados.obterDados(jsonMove, MoveRecord.class);

        if (listOfPokemonThatCanBeReceiveThisMmove.learned_by_pokemon().stream().map(pokemonRecord -> pokemonRecord.name().equalsIgnoreCase(move.getName())).isParallel())
            return true;
        log.info("This pokemon can be receive");
        return false;
    }

}
