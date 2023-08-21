package br.com.microservice.pokemon.repository;

import br.com.microservice.pokemon.domain.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, String> {
//    Pokemon findByNational_Pokedex_Number(Integer id);
}
