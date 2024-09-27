package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "move")
public class Move {

    @Id
    private String id;

    @JsonAlias("name")
    private String name;

    @JsonAlias("url")
    private String url;

    @JsonAlias("accuracy")
    private int accuracy;

    @JsonAlias("effect_chance")
    private String effect_chance;

    @JsonAlias("power")
    private Integer power;

    @JsonAlias("type")
    private Type type;

    @JsonProperty("damage_class")
    private Dano damage_class;

    @JsonAlias("learned_by_pokemon")
    private List<PokemonDTO> learned_by_pokemon;

}
