package br.com.microservice.attack.application.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "attack")
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movimento;
    private String traducao;
    private String tipo;
    private String geracao;
}
