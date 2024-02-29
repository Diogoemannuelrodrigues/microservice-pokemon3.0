package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "pokemon")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Battle {
}
