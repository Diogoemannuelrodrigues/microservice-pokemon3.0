package br.com.microservice.attack.application.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Document(collection = "attack")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attack {
    @Id
    private String id;

    private String movimento;

    private String traducao;

    private String tipo;

    private String geracao;
}
