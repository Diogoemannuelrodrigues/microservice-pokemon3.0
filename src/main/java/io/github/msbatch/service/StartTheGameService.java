package io.github.msbatch.service;

import io.github.msbatch.Move;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Slf4j
@Service
public class StartTheGameService {

    private final ConverterDados converterDados;

    public StartTheGameService(ConverterDados converterDados) {
        this.converterDados = converterDados;
    }

    public List<Move> startTheGame() {
        log.info("Starting/Reset the game.");
        String apiUrl = "https://pokeapi.co/api/v2/move/";
        List<Move> moves = new ArrayList<>();
        try {
            for (int i = 1; i < 919; i++) {
                String count = apiUrl + i;
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
                    log.info(move.getName() + " - This move can be received");
                    moves.add(move);
                } else {
                    throw new IOException("Failed to fetch move data: " + connection.getResponseCode());
                }
            }
        } catch (IOException e) {
            log.info( "An error occurred while fetching move data", e);
        }
        return moves;
    }
}
