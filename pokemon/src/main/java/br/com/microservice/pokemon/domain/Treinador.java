package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "treinador")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Treinador {

    @Id
    private String id;
    private String name;
    private List<Insignia> insignias;
    private List<Pokemon> pokemons = new ArrayList<>();
    private Bag bag;

}
