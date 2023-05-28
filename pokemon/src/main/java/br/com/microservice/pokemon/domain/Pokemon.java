package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pokemon")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pokemon {
    @Id
    private String id;
    private Integer national_pokedex_number;
    private String name;
    private BaseStats baseStats;
    private List<String> types;


}
