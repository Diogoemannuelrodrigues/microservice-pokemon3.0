package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeInfo {
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("type")
    private Type type;
}