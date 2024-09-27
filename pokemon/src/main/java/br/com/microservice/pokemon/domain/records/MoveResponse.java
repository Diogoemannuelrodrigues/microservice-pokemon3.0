package br.com.microservice.pokemon.domain.records;

import br.com.microservice.pokemon.domain.Move;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MoveResponse(String id,
                           String name,
                           Integer power,
                           int accuracy) {

    public static MoveResponse toEntityFromResponse(Move move) {
        return new MoveResponse(
                move.getId(),
                move.getName(),
                move.getPower(),
                move.getAccuracy()
        );
    }
}




