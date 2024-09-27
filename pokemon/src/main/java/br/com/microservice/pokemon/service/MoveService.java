package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.repository.MoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MoveService {

    private final MoveRepository moveRepository;


}
