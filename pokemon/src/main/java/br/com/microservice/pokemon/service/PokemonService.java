package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.EvolutionDTO;
import br.com.microservice.pokemon.domain.Pokemon;
import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.repository.PokemonRepository;
import br.com.microservice.pokemon.service.clients.GlitchfeignClient;
import br.com.microservice.pokemon.service.clients.PokeFeingClient;
import br.com.microservice.pokemon.utils.ConvertDados;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;
    @Autowired
    private PokeFeingClient pokeFeingClient;
    @Autowired
    private GlitchfeignClient glitchfeignClient;

    @Autowired
    private ConvertDados convertDados;

    @Autowired
    private ModelMapper mapper;

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

    public Optional<EvolutionDTO> getPokemonByGlitch(String name) {
        Gson gson = new Gson();
        var jsonResponse = glitchfeignClient.getPokemonDTO(name);
        EvolutionDTO[] array = gson.fromJson(jsonResponse, EvolutionDTO[].class);
        return Arrays.stream(array).findFirst();
    }

    public String starTheGame(){
        for (int i = 1; i <= 2; i++){
            var poke = pokeFeingClient.getPkemonById(i);
            var pokeConverted = convertDados.obterDados(poke, Pokemon.class);
            repository.save(pokeConverted);
            log.info(String.valueOf(pokeConverted));
        }

        return "the game can start now";
    }

    public List<PokemonDTO> findAllPokemon() throws IOException {
        return repository
                .findAll()
                .stream()
                .map(pokemon -> mapper.map(pokemon, PokemonDTO.class))
                .collect(Collectors.toList());

    }
}
