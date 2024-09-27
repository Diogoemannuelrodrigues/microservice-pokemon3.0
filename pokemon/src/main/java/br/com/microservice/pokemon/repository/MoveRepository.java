package br.com.microservice.pokemon.repository;

import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.domain.records.MoveResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoveRepository extends MongoRepository<Move, String> {

    Optional<Move> findByName(String name);
}
