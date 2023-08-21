package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    @Autowired
    private ConvertDados convertDados;

    public Pokemon convertPokemon(String json) {
        var pokemon = convertDados.obterDados(json, Pokemon.class);
        return repository.save(pokemon);
    }

    public Pokemon findPokemon(String name){
        return repository.findByName(name);
    }
}
