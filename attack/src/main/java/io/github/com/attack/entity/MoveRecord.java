package io.github.com.attack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record MoveRecord(@JsonProperty("learned_by_pokemon") List<PokemonRecord> learned_by_pokemon) {
    public record PokemonRecord(String name, String url){}
}
