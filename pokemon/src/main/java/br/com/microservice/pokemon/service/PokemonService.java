package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.repository.PokemonRepository;
import br.com.microservice.pokemon.service.clients.PokeFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    @Autowired
    private PokeFeingClient pokeFeingClient;

    @Autowired
    private ConvertDados convertDados;

    public Pokemon convertPokemon(String json) {
        var pokemon = convertDados.obterDados(json, Pokemon.class);
        return repository.save(pokemon);
    }

    public Optional<Pokemon> findById(String id){
        return repository.findById(id);
    }

    public PokemonDTO getPokemon(String name){
        return pokeFeingClient.getPkemonDTO(name);
    }
}
