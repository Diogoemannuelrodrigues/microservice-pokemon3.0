package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseStats {
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer speed;
    private Integer special;
}
