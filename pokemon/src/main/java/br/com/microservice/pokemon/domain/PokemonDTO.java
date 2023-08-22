package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDTO {

    private String id;
    @JsonAlias("abilities")
    private List<AbilityInfo> abilities;
    @JsonAlias("base_experience")
    private String base_experience;
    private String name;
    @JsonAlias("moves")
    private List<MoveInfo> moves;
    @JsonAlias("stats")
    private List<StatInfo> states;

    @JsonAlias("types")
    private List<TypeInfo> types;
    private Integer weight;
}
