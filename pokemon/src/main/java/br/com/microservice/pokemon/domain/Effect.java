package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@JsonIgnoreProperties(ignoreUnknown = true)
public class Effect {
    private String name;
}
