package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public List<Pokemon> saveAll(List<Pokemon> pokemons){
        return repository.saveAll(pokemons);
    }
}
