package br.com.microservice.pokemon.repository;

import br.com.microservice.pokemon.domain.Treinador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinadorRepository extends MongoRepository<Treinador, String> {
    Treinador findByNameIgnoreCase(String name);
}
