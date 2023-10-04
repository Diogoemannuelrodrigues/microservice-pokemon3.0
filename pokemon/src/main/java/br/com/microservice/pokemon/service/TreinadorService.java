package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Treinador;
import br.com.microservice.pokemon.domain.TreinadorDto;
import br.com.microservice.pokemon.repository.PokemonRepository;
import br.com.microservice.pokemon.repository.TreinadorRepository;
import br.com.microservice.pokemon.utils.exceptions.TreinadorNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        if (isNull(existTrainer)) {
            return treinadorRepository.save(mapper.map(treinadorDto, Treinador.class));
        }
        throw new TreinadorNaoEncontradoException("Treinador nao encontrado");
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

    public Treinador adicionaPokemonParaTreinador(String treinadorName) {
        Random random = new Random();
        int idPokemon = random.nextInt(148) + 1;

        var pokemon = pokemonRepository.findById(String.valueOf(idPokemon));
        var treinador = treinadorRepository.findByNameIgnoreCase(treinadorName);

        pokemon.ifPresent(pokemon1 -> pokemon1.getMoveInfos().clear());

        if(treinador.getPokemons().size() > 0 && pokemon.isPresent()){
            treinador.getPokemons().add(pokemon.get());
        }

        treinadorRepository.save(treinador);
        return treinador;
    }




}
