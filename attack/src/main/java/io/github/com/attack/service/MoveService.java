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

    public String startTheGame(){
        List<Move> moves = new ArrayList<>();

        for (int i = 1; i<919; i++){
            var move = moveFeingClient.getMove(i);
            var moveConverted = converterDados.obterDados(move, Move.class);
            moves.add(moveConverted);
            moveRepository.save(moveConverted);
        }
        log.info("{}", moves.stream().map(Move::getName).toList());
        return "Stating the game";
    }

    public String verifyIfPokemonCanToReceiveMove(String nameMove, String pokemon){
        var move = moveRepository.findByName(nameMove);
        var jsonMove = moveFeingClient.getMove(move.getId());
        var listOfPokemonThatCanBeReceiveThisMmove = converterDados.obterDados(jsonMove, MoveRecord.class);

        return verifyIfContainsPokemonInTheList(pokemon, listOfPokemonThatCanBeReceiveThisMmove);
    }

    public static String verifyIfContainsPokemonInTheList(String pokemon, MoveRecord listOfPokemonThatCanBeReceiveThisMmove) {
        if (listOfPokemonThatCanBeReceiveThisMmove
                .learned_by_pokemon()
                .stream()
                .anyMatch(pokemonRecord -> pokemonRecord.name().equalsIgnoreCase(pokemon))){
            return "This pokemon ( "+ pokemon +" ) can be received this move";

        }
        return "This pokemon ( "+ pokemon +" ) cannot be received this move";
    }


}
