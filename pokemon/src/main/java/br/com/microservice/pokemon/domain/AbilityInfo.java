package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityInfo {

    @JsonProperty("ability")
    private Ability ability;
    @JsonProperty("is_hidden")
    private boolean isHidden;
    private int slot;
}
