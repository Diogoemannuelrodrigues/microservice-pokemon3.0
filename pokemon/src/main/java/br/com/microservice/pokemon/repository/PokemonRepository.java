package br.com.microservice.pokemon.repository;

import br.com.microservice.pokemon.domain.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, String> {

    Pokemon findByName(String name);
}
