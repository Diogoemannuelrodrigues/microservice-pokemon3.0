package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pokemon")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    @Id
    private String id;
    @JsonAlias("abilities")
    private List<AbilityInfo> abilities;
    @JsonAlias("base_experience")
    private String baseExperience;
    private String name;
    @JsonAlias("moves")
    private List<MoveInfo> moves;
    @JsonAlias("sprites")
    private Sprites sprites;
    @JsonAlias("stats")
    private List<StatInfo> states;
    @JsonAlias("types")
    private List<TypeInfo> types;
    private Integer weight;

}
