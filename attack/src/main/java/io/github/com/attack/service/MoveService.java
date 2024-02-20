package io.github.com.attack.service;

import io.github.com.attack.entity.Move;
import io.github.com.attack.repository.MoveRepository;
import io.github.com.attack.service.clients.MoveFeingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        for (int i = 1; i<919; i++){
            var move = moveFeingClient.getMove(i);
            var moveConverted = converterDados.obterDados(move, Move.class);
            log.info(String.valueOf(moveConverted));
        }
        return "Stating the game";
    }

}
