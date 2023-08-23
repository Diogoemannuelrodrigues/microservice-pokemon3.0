package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatInfo {
    @JsonProperty("base_stat")
    private Integer base_stat;

    private Integer effort;

    @JsonProperty("stat")
    private Stat stats;
}
