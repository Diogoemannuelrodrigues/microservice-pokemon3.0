package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.EvolutionDTO;
import br.com.microservice.pokemon.domain.MoveInfo;
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
        var pokemon = repository.findById(id);
        var moves = pokemon.get().getMoves().subList(0, 4);
        pokemon.get().setMoves(moves);
        return pokemon;
    }

    public PokemonDTO getPokemon(String name){
        return pokeFeingClient.getPkemonDTO(name);
    }

    public Optional<EvolutionDTO> getEvolucaoPokemonByGlitch(String name) {
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

    public Optional<MoveInfo> getAdicionaMove(Pokemon pokemon, String nameMove){
        return pokemon
                .getMoves() //pega os moves
                .stream() //faz o stream
                .filter(moveInfo -> moveInfo.getMove().getName().contains(nameMove)) //filtra por nameMOve
                .findFirst(); //Retorna o 1 que achou
    }

    public List<PokemonDTO> findAllPokemon() {
        return repository
                .findAll()
                .stream()
                .map(pokemon -> mapper.map(pokemon, PokemonDTO.class))
                .collect(Collectors.toList());

    }
}
