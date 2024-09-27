package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.domain.records.MoveResponse;
import br.com.microservice.pokemon.repository.MoveRepository;
import br.com.microservice.pokemon.utils.ConvertDados;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoveService {

    private final MoveRepository moveRepository;
    private final ConvertDados converterDados;


    public void start() {
        log.info("Starting/Reset the game.");
        String apiUrl = "https://pokeapi.co/api/v2/move/";
        List<Move> moves = new ArrayList<>();
        try {
            for (int i = 1; i < 919; i++) {
                String count = apiUrl + i;
                HttpURLConnection connection = getHttpURLConnection(count);

                filtragem(connection, moves);
            }
        } catch (IOException e) {
            log.info("An error occurred while fetching move data", e);
        }

        log.info("{}", moves);

    }

    private void filtragem(HttpURLConnection connection, List<Move> moves) throws IOException {
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
            log.info("{} - {}", move.getId(), move.getName());
            moveRepository.save(move);
        } else {
            throw new IOException("Failed to fetch move data: " + connection.getResponseCode());
        }
    }

    private static HttpURLConnection getHttpURLConnection(String count) throws IOException {
        URL url = new URL(count);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    public Page<MoveResponse> movesPages(Integer page, Integer size, String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return moveRepository.findAll(pageRequest).map(MoveResponse::toEntityFromResponse);
    }
}
