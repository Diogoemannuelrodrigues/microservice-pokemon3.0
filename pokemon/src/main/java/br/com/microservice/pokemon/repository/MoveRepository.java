package br.com.microservice.pokemon.repository;

import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.domain.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends MongoRepository<Move, String> {
    Move findByName(String name);
}
