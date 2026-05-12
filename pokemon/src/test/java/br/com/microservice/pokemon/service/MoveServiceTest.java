package br.com.microservice.pokemon.service;

import br.com.microservice.pokemon.domain.Leanerd;
import br.com.microservice.pokemon.domain.Move;
import br.com.microservice.pokemon.repository.MoveRepository;
import br.com.microservice.pokemon.utils.ConvertDados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class MoveServiceTest {

    @Mock
    private ConvertDados convertDados;

    @Mock
    private MoveRepository moveRepository;

    @InjectMocks
    private MoveService moveService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTrueQuandoPokemonPodeAprenderMovimento() {
        Leanerd pikachu = Leanerd.builder().name("pikachu").url("url").build();
        Leanerd charmander = Leanerd.builder().name("charmander").url("url").build();
        Move move = Move.builder().name("thunderbolt").learned_by_pokemon(Arrays.asList(pikachu, charmander)).build();
        when(moveRepository.findByName("thunderbolt")).thenReturn(move);
        Boolean resultado = moveService.podeAprenderOhMovimento("thunderbolt", "pikachu");
        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoPokemonNaoPodeAprenderMovimento() {
        Leanerd bulbasaur = Leanerd.builder().name("bulbasaur").url("url").build();
        Move move = Move.builder().name("flamethrower").learned_by_pokemon(Collections.singletonList(bulbasaur)).build();
        when(moveRepository.findByName("flamethrower")).thenReturn(move);
        Boolean resultado = moveService.podeAprenderOhMovimento("flamethrower", "pikachu");
        assertFalse(resultado);
    }
}
