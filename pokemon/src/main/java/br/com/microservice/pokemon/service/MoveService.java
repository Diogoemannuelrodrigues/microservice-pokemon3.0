package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.repository.MoveRepository;
import br.com.microservice.pokemon.utils.ConvertDados;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MoveService {

    private final ConvertDados converterDados;
    private final MoveRepository moveRepository;
    private static final String API_URL = "https://pokeapi.co/api/v2/move/";

    public MoveService(ConvertDados converterDados, MoveRepository moveRepository) {
        this.converterDados = converterDados;
        this.moveRepository = moveRepository;
    }

    public void startTheGame() {
        log.info("Starting/Reset the game.");

        List<Move> moves = new ArrayList<>();
        try {
            for (int i = 1; i < 919; i++) {
                String count = API_URL + i;
                URL url = new URL(count);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder build = new StringBuilder();
                    String line;
                    while ((line = buf.readLine()) != null) {
                        build.append(line);
                    }
                    buf.close();
                    String moveData = build.toString();
                    Move move = converterDados.obterDados(moveData, Move.class);
                    moves.add(move);
                } else {
                    throw new IOException("Failed to fetch move data: " + connection.getResponseCode());
                }
            }
        } catch (IOException e) {
            log.info("An error occurred while fetching move data", e);
        }
        moveRepository.saveAll(moves);
    }

    public Boolean podeAprenderOhMovimento(String nameMove, String namePokemon) {
            Move move = this.moveRepository.findByName(nameMove);
            return move.getLearned_by_pokemon()
                    .stream()
                    .anyMatch(pokemon -> pokemon.getName()
                                    .equalsIgnoreCase(namePokemon));

    }
}