package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "insignia")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Insignia {
    @Id
    private String id;

    private String nome;

    private String base;

    private String Doador;
}
