package br.com.microservice.attack.application.repository;

import br.com.microservice.attack.application.core.domain.Attack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttackRepository extends MongoRepository <Attack, String> {

    Attack findByName(String name);

    List<Attack> findByType(String type);
}
