package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Treinador;
import br.com.microservice.pokemon.domain.TreinadorDto;
import br.com.microservice.pokemon.repository.TreinadorRepository;
import br.com.microservice.pokemon.utils.exceptions.TreinadorNaoEncontradoException;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private ModelMapper mapper;

    public Treinador save(TreinadorDto treinadorDto){
        var existTrainer = findByName(treinadorDto.getName());
        if(isNull(existTrainer)){
            return treinadorRepository
                    .save(mapper.map(treinadorDto, Treinador.class));
        }
        throw new TreinadorNaoEncontradoException("Treinador nao encontrado");
    }

    public Treinador findByName(String name){
        return treinadorRepository.findByNameIgnoreCase(name);
    }

    public Optional<Treinador> findById(String id){
        var treinador = treinadorRepository.findById(id);

        if (treinador.isPresent()){
            return treinador;
        }
        throw new TreinadorNaoEncontradoException("Treiandor nao encontrado com esse id ="+ id);
    }
}
