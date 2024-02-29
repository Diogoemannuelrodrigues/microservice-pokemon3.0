package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.PokemonDTO;
import br.com.microservice.pokemon.domain.Treinador;
import br.com.microservice.pokemon.domain.TreinadorDto;
import br.com.microservice.pokemon.repository.PokemonRepository;
import br.com.microservice.pokemon.repository.TreinadorRepository;
import br.com.microservice.pokemon.utils.exceptions.TreinadorNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class TreinadorService {

    private final StoneService stoneService;
    private final TreinadorRepository treinadorRepository;
    private final PokemonRepository pokemonRepository;
    private final ModelMapper mapper;

    public Treinador save(TreinadorDto treinadorDto) {
        var existTrainer = findByName(treinadorDto.getName());
        if (isNull(existTrainer)){
            return treinadorRepository.save(mapper.map(treinadorDto, Treinador.class));
        }
        throw new TreinadorNaoEncontradoException("Trainer: "+ treinadorDto.getName()+ " already exists");
    }

    public Treinador findByName(String name) {
        return treinadorRepository.findByNameIgnoreCase(name);
    }

    public Optional<Treinador> findById(String id) {
        var treinador = treinadorRepository.findById(id);

        if (treinador.isPresent()) {
            return treinador;
        }
        throw new TreinadorNaoEncontradoException("Treiandor nao encontrado com esse id =" + id);
    }

    public Treinador addPokemonToTrainer(String trainer) {

        int idPokemon = getIdRandom();

        var pokemon = pokemonRepository.findById(String.valueOf(idPokemon));
        var treinador = treinadorRepository.findByNameIgnoreCase(trainer);

        if (treinador.getPokemons() == null) {
            treinador.setPokemons(new ArrayList<>());
        }

        pokemon.ifPresent(pokemon1 -> pokemon1.getMoveInfos().clear());
        pokemon.ifPresent(pokemon2 -> treinador.getPokemons().add(pokemon2));

        treinadorRepository.save(treinador);
        return treinador;
    }

    public int getIdRandom(){
        Random random = new Random();
        return random.nextInt(148) + 1;
    }


    //TODO
//    public String adicionaPokemonInicial(){
//    Adiciona pokemons iniciais, Bulbasaur, Squertle and Charmander
//    }



}
