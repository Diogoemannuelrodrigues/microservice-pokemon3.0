package br.com.microservice.attack.application.repository;

import br.com.microservice.attack.application.core.domain.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttackRepository extends JpaRepository<Move, Integer> {
    Move findByName(String movimento);

    List<Move> findByType(String tipo);
}
