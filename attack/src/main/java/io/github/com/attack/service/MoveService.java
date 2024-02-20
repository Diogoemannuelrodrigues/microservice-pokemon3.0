package io.github.com.attack.service;

import io.github.com.attack.entity.Move;
import io.github.com.attack.repository.MoveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoveService {

    private final MoveRepository moveRepository;

    public List<Move> getMove() {
        return moveRepository
                .findAll()
                .stream()
                .toList();
    }
}
