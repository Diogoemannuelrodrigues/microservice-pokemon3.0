package br.com.microservice.pokemon.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dano(@JsonAlias("name") String name, @JsonAlias("url") String url) {
}
