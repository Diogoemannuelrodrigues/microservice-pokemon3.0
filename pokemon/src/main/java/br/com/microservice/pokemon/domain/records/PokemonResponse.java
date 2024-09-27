package br.com.microservice.pokemon.domain.records;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.domain.TypeInfo;

import java.util.List;

public record PokemonResponse(String id,
                              String name,
                              List<TypeInfo> types) {

    public static PokemonResponse toEntityFromResponse(Pokemon pokemon) {
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getTypes()
        );
    }

}
